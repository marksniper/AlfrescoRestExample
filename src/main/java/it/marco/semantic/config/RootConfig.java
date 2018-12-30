package it.marco.semantic.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("it.marco.semantic")
@PropertySources({
        @PropertySource("classpath:configuration.properties")
})

public class RootConfig implements WebMvcConfigurer
{
    private final static Logger log = LoggerFactory.getLogger(RootConfig.class);
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        p.setNullValue("");
        return p;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.info(">>>>> CONFIGURING DefaultServletHandlerConfigurer: enabled");
        configurer.enable();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        String defEncoding ="UTF-8";
        multipartResolver.setDefaultEncoding(defEncoding);
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }
    @Bean
    public InternalResourceViewResolver htmlViewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        String viewPath = "/WEB-INF/views/";
        viewResolver.setPrefix(viewPath);
        viewResolver.setSuffix("");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        String originLocation = "/WEB-INF/views/resources/";
        String targetLocation = "/res/**";
        registry.addResourceHandler(targetLocation).addResourceLocations(originLocation);
    }


}