package it.marco.semantic.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(WebInitializer.class);

    public void onStartup(ServletContext servletContext)
    {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(RootConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",	new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        String filterProxyName = "springSecurityFilterChain";
        FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter(filterProxyName, new DelegatingFilterProxy(filterProxyName));
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
    }
}