package org.springframework.boot.autoconfigure.bootswaggermvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(ThymeleafAutoConfiguration.class)
@ConditionalOnWebApplication
public class LightAdminAutoConfig extends SpringBootServletInitializer{
	
	/* Please uncomment for deploying as a web module to servlet container */
    /**
     * public void onStartup(ServletContext servletContext) throws ServletException {
     * LightAdmin.configure(servletContext)
     * .basePackage("org.lightadmin.boot.administration")
     * .baseUrl("/admin")
     * .security(false)
     * .backToSiteUrl("http://lightadmin.org");
     * super.onStartup(servletContext);
     * }
     */
	
	/* Used for running in "embedded" mode */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                LightAdmin.configure(servletContext)
                        //.basePackage("org.lightadmin.boot.administration")
                        .baseUrl("/lightadmin")
                        .security(false)
                        .backToSiteUrl("http://lightadmin.org");

                new LightAdminWebApplicationInitializer().onStartup(servletContext);
            }
        };
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LightAdminAutoConfig.class);
    }
}