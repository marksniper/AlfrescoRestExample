package it.marco.semantic.Alfresco;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.marco.semantic.model.Utente;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NodeAlfresco
{
    @Autowired
    private Environment env;

    private final static Logger log LoggerFactory.getLogger(NodeAlfresco.class);

    public JSONObject getNodeInfo(String idNode)
    {
        log.info("Request node info. Node id: "+idNode);
        try
        {
            log.debug("URL: "+env.getProperty("url.alfresco.api")+"/nodes/" + idNode);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(env.getProperty("url.alfresco.api")+"/nodes/" + idNode)
                    .basicAuth(Utente.getInstance().getUsername(), Utente.getInstance().getPassword())
                    .asJson();
            log.info("Response JSON:"+jsonResponse.getBody().getObject().toString());
            return jsonResponse.getBody().getObject();

        }
        catch (UnirestException e)
        {
            log.error("Unirest exception. Check log");
            e.printStackTrace();
            return new JSONObject().put("error","Error to get node information");
        }

    }

    public JSONObject getChildInformation(String idNode) {
        log.info("Request node children");
        try
        {
            log.debug("URL: "+env.getProperty("url.alfresco.api")+"/nodes/" + idNode + "/children"/*+provaprova*/);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(env.getProperty("url.alfresco.api")+"/nodes/" + idNode + "/children"/*+provaprova*/)
                    .basicAuth(Utente.getInstance().getUsername(), Utente.getInstance().getPassword())
                    .asJson();
            JSONObject response = jsonResponse.getBody().getObject();
            log.info("Has more items? "+response.getJSONObject("list").getJSONObject("pagination").get("hasMoreItems"));

            if( (boolean) response.getJSONObject("list").getJSONObject("pagination").get("hasMoreItems") )
            {
                log.info("Set maxItems equals to totalItems");
                int maxItems = (int) response.getJSONObject("list").getJSONObject("pagination").get("totalItems");
                response =  getChildInformation(idNode,maxItems);
            }
            log.info("Response JSON:"+response.toString());
            return response;
        } catch (UnirestException e)
        {
            e.printStackTrace();
            log.error("Unirest exception. Check log");
            return new JSONObject().put("error","Error to get data");
        }
    }


    private JSONObject getChildInformation(String idNode, int maxItem) {
        log.info("Request node children setting maxItem equal to totalItems");
        try
        {
            log.debug("URL: "+env.getProperty("url.alfresco.api")+"/nodes/" + idNode + "/children?maxItems="+maxItem);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(env.getProperty("url.alfresco.api")+"/nodes/" + idNode + "/children?maxItems="+maxItem)
                    .basicAuth(Utente.getInstance().getUsername(), Utente.getInstance().getPassword())
                    .asJson();
            log.info("Response JSON:"+jsonResponse.getBody().getObject().toString());
            return jsonResponse.getBody().getObject();
        } catch (UnirestException e)
        {
            e.printStackTrace();
            log.error("Unirest exception. Check log");
            return new JSONObject().put("error","Error to get data");
        }

    }

    public JSONObject creaCartella(String name, String title, String description, String idNodoPadre)
    {
        log.info("Creo cartella");
        try
        {
            log.debug("Connetto ad Alfresco. URL: "+env.getProperty("url.alfresco.api")+"/nodes/" + idNodoPadre + "/children");

            JSONObject bodyRequest = new JSONObject();
            JSONObject propietes = new JSONObject();
            bodyRequest.put("name", name);
            bodyRequest.put("nodeType", "cm:folder");
            propietes.put("cm:title", title);
            propietes.put("cm:description", description);
            bodyRequest.put("properties", propietes);

            log.debug("Prepare request body JSON: "+bodyRequest.toString());
            HttpResponse<JsonNode> jsonResponse = Unirest.post(env.getProperty("url.alfresco.api")+"/nodes/" + idNodoPadre + "/children")
                    .basicAuth(Utente.getInstance().getUsername(), Utente.getInstance().getPassword())
                    .body(bodyRequest)
                    .asJson();
            log.info("Response JSON: "+jsonResponse.getBody().getObject().toString());
            return jsonResponse.getBody().getObject();
        }
        catch (UnirestException e)
        {
            log.error("Unirest exception. Check log");
            e.printStackTrace();
            return new JSONObject().put("error", "Error to create directory/folder");
        }

    }


}
