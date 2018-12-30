package it.marco.semantic.Alfresco;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.marco.semantic.model.Utente;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ManagerFileAlfresco
{
    @Autowired
    private Environment env;

    private final static Logger log LoggerFactory.getLogger(ManagerFileAlfresco.class);

    public JSONObject caricaFileSuAlfresco(MultipartFile file, String idCartellaDestinazione)
    {
        log.info("Prepare file upload request");
        try
        {
            log.debug("URl: "+env.getProperty(("url.alfresco.upload"))+"" +
                    ">>>>> File name: "+file.getOriginalFilename()+">>>>> folder: "+"workspace://SpacesStore/" + idCartellaDestinazione);
            HttpResponse<JsonNode> jsonResponse = Unirest.post(env.getProperty("url.alfresco.upload"))
                    .basicAuth(Utente.getInstance().getUsername(), Utente.getInstance().getPassword())
                    .field("filedata", file.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, file.getOriginalFilename())
                    .field("filename", file.getOriginalFilename())
                    .field("destination", "workspace://SpacesStore/" + idCartellaDestinazione)
                    .field("description", "test description")
                    .field("thumbnails", "")
                    .field("overwrite", "")
                    .field("majorversion", "")
                    .field("content type", "")
                    .field("uploaddirectory", "")
                    .field("updateNodeRef", "")
                    .field("siteid", "")
                    .field("containerid", "")
                    .asJson();
            log.info("Response JSON: "+jsonResponse.getBody().getObject().toString());
            return jsonResponse.getBody().getObject();

        } catch (UnirestException | IOException e)
        {
            log.error("Unirest or InputOutput (IO) exception. Please check log");
            e.printStackTrace();
            return new JSONObject().put("error","Error to upload file");
        }

    }

}