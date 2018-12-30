package it.marco.semantic.controllers;

import it.marco.semantic.model.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController
{
    public static final Logger log =  LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
       
        String pageName = "index.jsp";
        log.info("Load "+pageName);
        ModelAndView view = new ModelAndView(pageName);
        view.addObject("utente", Utente.getInstance());
        log.debug("Add object in view: utente");
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        log.debug("roles"+ auth.getAuthorities());
        log.debug("role: "+Utente.getInstance().getRole());
        return view;
    }

    @GetMapping(value = {"/accedi"})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
    {
        String pageName = "login.jsp";
        log.info("Load "+pageName);
        ModelAndView view = new ModelAndView(pageName);
        return view;
    }

    @GetMapping(value = "/prova")
    public ModelAndView prova(HttpServletRequest request, HttpServletResponse response) {
        String pageName = "prova.html";
        log.info("Load "+pageName);
        ModelAndView view = new ModelAndView(pageName);
        return view;
    }

    @GetMapping(value = "/semantic")
    public ModelAndView semantic(HttpServletRequest request, HttpServletResponse response) {
        String pageName = "semantic1.jsp";
        log.info("Load "+pageName);
        ModelAndView view = new ModelAndView(pageName);
        return view;
    }

}
