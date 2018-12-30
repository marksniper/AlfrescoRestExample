package it.marco.semantic.config;

import it.marco.semantic.utilities.UtilDate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ResourceBundle;

@Component
public class SessionListener implements HttpSessionListener
{
    private ResourceBundle configProp = ResourceBundle.getBundle("configuration");

    private int intervalSeconds = 3600;

    public SessionListener() {

    }

    @Override
    public void sessionCreated(HttpSessionEvent event)
    {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        event.getSession().setMaxInactiveInterval(intervalSeconds);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("SESSION-ID [" + event.getSession().getId() + "] - Sessione distrutta.");

    }

}
