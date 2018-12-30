package it.marco.semantic.Alfresco;

import com.google.gson.Gson;
import it.marco.semantic.model.Alfresco.AlfrescoNode;
import it.marco.semantic.model.Alfresco.Node;
import it.marco.semantic.model.Alfresco.Tree;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAllFile
{
    @Autowired
    private Environment env;
    @Autowired
    private NodeAlfresco nodeAlfresco;

    private Tree<AlfrescoNode> albero;
    private Tree<JSONObject> alberoJSON;

    private final static Logger log LoggerFactory.getLogger(ShowAllFile.class);

    public Tree<AlfrescoNode> createTree()
    {
        Node<JSONObject> rootJSON = new Node <>(nodeAlfresco.getNodeInfo("-my-"));
        log.info("Create the root of the tree");
        AlfrescoNode alfrescoRoot = new Gson().fromJson(nodeAlfresco.getNodeInfo("-my-").toString(), AlfrescoNode.class);
        Node<AlfrescoNode> root = new Node<AlfrescoNode>(alfrescoRoot);
        log.debug("Build tree");
        buildTree(root);
        buildTreeJSON(rootJSON);
        albero = new Tree <>(root);
        alberoJSON = new Tree <>(rootJSON);
        return albero;

    }


    private void buildTreeJSON(Node<JSONObject>  parent)
    {
        List<JSONObject> foglie = getFigliJSON(parent);
        for(JSONObject foglia : foglie )
        {
            if(((boolean) foglia.getJSONObject("entry").get("isFolder") || (boolean) foglia.getJSONObject("entry").get("isFile"))
                    && ( foglia.getJSONObject("entry").get("nodeType").equals("cm:folder") || foglia.getJSONObject("entry").get("nodeType").equals("cm:content") ))
            {
                log.info("id "+foglia.getJSONObject("entry").get("id")+ " nome: "+foglia.getJSONObject("entry").get("name"));
                Node<JSONObject> fogliaNodo = new Node <>(foglia);
                parent.addChild(fogliaNodo);
                buildTreeJSON(fogliaNodo);
            }
        }
    }



    private void buildTree(Node<AlfrescoNode> parent)
    {
        List<AlfrescoNode> foglie = getFigli(parent);
        for (AlfrescoNode foglia : foglie)
        {
            if (((foglia.getEntry().isIsFile()) || foglia.getEntry().isIsFolder())
                    &&
                    (foglia.getEntry().getNodeType().equals("cm:folder") || foglia.getEntry().getNodeType().equals("cm:content")))
            {
                Node<AlfrescoNode> fogliaNodo = new Node <>(foglia);
                parent.addChild(fogliaNodo);
                buildTree(fogliaNodo);
            }
        }
    }

    private List<JSONObject> getFigliJSON(Node<JSONObject> parent)
    {
        ArrayList<JSONObject> foglieInterne = new ArrayList <>();
        try
        {
            JSONObject response =
                    nodeAlfresco.getChildInformation(""+parent.getData().getJSONObject("entry").get("id"));
            log.info("Response JSON: "+response.toString());
            JSONArray getFolder = response.getJSONObject("list").getJSONArray("entries");
            for (int i =0; i< getFolder.length(); i++)
            {
                JSONObject foglia = getFolder.getJSONObject(i);
                foglieInterne.add(foglia);
            }

            return foglieInterne;
        }
        catch(JSONException e)
        {
            log.error("Error to convert a JSON");
            e.printStackTrace();
            return new ArrayList <>();
        }
    }



    private ArrayList<AlfrescoNode> getFigli(Node<AlfrescoNode> parent)
    {
            ArrayList<AlfrescoNode> foglieInterne = new ArrayList <>();
            try
            {
                JSONObject response = nodeAlfresco.getChildInformation(parent.getData().getEntry().getId());
                JSONArray getFolder = response.getJSONObject("list").getJSONArray("entries");
                for (int i =0; i< getFolder.length(); i++)
                {
                    JSONObject foglia = getFolder.getJSONObject(i);
                    AlfrescoNode cartella = new Gson().fromJson(foglia.toString(), AlfrescoNode.class);
                    foglieInterne.add(cartella);
                }

                return foglieInterne;
            }
            catch(JSONException e)
            {
                log.error("Error to convert JSON");
                e.printStackTrace();
                return new ArrayList <>();
            }
    }
}
