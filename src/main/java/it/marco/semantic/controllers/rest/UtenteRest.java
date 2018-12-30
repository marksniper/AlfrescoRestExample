package it.marco.semantic.controllers.rest;

import it.marco.semantic.model.Utente;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/utente")
public class UtenteRest
{
    private final static Logger log = LoggerFactory.getLogger(UtenteRest.class);
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity test() {
        log.info("Test");
        return ResponseEntity.ok(new JSONObject().put("info", "Test is successful").toString());
    }

    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity user() {
        log.info("Get user Java infromation. Attention: the infromation is model Java based, NOT Alfresco");
        return ResponseEntity.ok(new JSONObject()
                .put("user", Utente.getInstance().getUsername())
                .put("role",Utente.getInstance().getRole())
                .toString());
    }

}
