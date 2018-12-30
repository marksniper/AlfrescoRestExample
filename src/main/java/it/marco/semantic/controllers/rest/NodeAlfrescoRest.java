package it.marco.semantic.controllers.rest;


import com.google.gson.Gson;
import it.marco.semantic.Alfresco.ManagerFileAlfresco;
import it.marco.semantic.Alfresco.NodeAlfresco;
import it.marco.semantic.Alfresco.ShowAllFile;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/node")
public class NodeAlfrescoRest
{
    @Autowired
    private  ManagerFileAlfresco managerFileAlfresco;
    @Autowired
    private NodeAlfresco nodeAlfresco;
    @Autowired
    private ShowAllFile showAllFile;

    private final static Logger log = LoggerFactory.getLogger(NodeAlfrescoRest.class);

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity test(HttpServletRequest request)
    {
        log.info("Test");
        return ResponseEntity.ok(new JSONObject().put("info","Test successful").toString());
    }

    @PostMapping(value = "/treedirectory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity treeDirectory(HttpServletRequest request)
    {
        log.info("Prepare the file view tree");
        return ResponseEntity.ok(new JSONObject(new Gson().toJson(showAllFile.createTree())).toString());
    }

    @GetMapping(value = "/info/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity getInfoNode(@PathVariable String id) {
        log.info("Get node info. ID NODE: "+id);
        return ResponseEntity.ok(nodeAlfresco.getNodeInfo(id).toString());
    }

    @GetMapping(value = "/child/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity getChildrenNode(@PathVariable String id)
    {
        log.info("Gather information about the children of a node");
        return new ResponseEntity<>(nodeAlfresco.getChildInformation(id).toString(), HttpStatus.OK);
    }

    @PostMapping("/fileUpload")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file, @RequestParam  String idCartella) {
        log.info("Upload file service. (Using MultipartFile and InputStream)."+">>>>>>Name: "+file.getOriginalFilename()
        +">>>>>>ID folder: "+idCartella);
        if(idCartella.equals(""))
        {
            log.error("Id parent folder is null. Bad request is generated");
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        if (!Objects.requireNonNull(file.getOriginalFilename()).isEmpty())
        {
            return ResponseEntity.ok( managerFileAlfresco.caricaFileSuAlfresco(file, idCartella).toString());
        }
        else
        {
            log.error("File error. Bad request is generated");
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/createDirectory")
    public ResponseEntity<String> createDirectory(@RequestParam("name") String name, @RequestParam("title") String title, @RequestParam("description") String description,
                                          @RequestParam("idNodoPadre") String idNodoPadre)
    {
       log.info("Create folder");
        JSONObject result = nodeAlfresco.creaCartella(name, title, description, idNodoPadre);
        return ResponseEntity.ok(result.toString());
    }

}
