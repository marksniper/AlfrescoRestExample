package it.marco.semantic.config;

import it.marco.semantic.utilities.UtilDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
@Controller
public class FilterSite implements Filter
{
    @Override
    public void init(FilterConfig filterConfig)
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException
    {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        String loginURI = request.getContextPath() + "/accedi";
        System.out.println("Session NOT NULL: " + session.getId() + ". MaxInactiveInterval: " + session.getMaxInactiveInterval() + "\nLa sessione è creata: " +
                "" + UtilDate.convertDate(session.getCreationTime()) + "Last access: " +
                "" + UtilDate.convertDate(session.getLastAccessedTime())
        );
        if (session == null) {
            response.sendRedirect(loginURI);
        }
        session.setMaxInactiveInterval(60);
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
    }
}
