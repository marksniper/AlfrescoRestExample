package it.marco.semantic.Alfresco;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.marco.semantic.model.Utente;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LoginAlfresco
{
    private final static Logger log LoggerFactory.getLogger(LoginAlfresco.class);

    @Autowired
    private Environment env;

    public String loginPost() {
        log.info("Request login ticket to Alfresco");
        JSONObject credenziali = new JSONObject();
        credenziali.put("username", Utente.getInstance().getUsername());
        credenziali.put("password", Utente.getInstance().getPassword());
        try
        {
            log.debug("URL: "+env.getProperty("url.alfresco.login"));
            HttpResponse <JsonNode> jsonResponse = Unirest.post(env.getProperty("url.alfresco.login"))
                    .header("accept", "application/json").body(credenziali).asJson();
            JSONObject result = jsonResponse.getBody().getObject();
            result = result.getJSONObject("data");
            log.info("User is authenticated. Authentication ticket is:"+result.get("ticket").toString());
            return result.get("ticket").toString();

        } catch (UnirestException | JSONException e)
        {
            log.error("Unirest or JSON exception. Please check log");
            e.printStackTrace();
            return new String();
        }
    }

}