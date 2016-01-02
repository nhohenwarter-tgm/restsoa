package brinnichHohenwarter.soa.localhost.soa.search;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soa/*");
    }

    @Bean(name = "search")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema searchschema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SearchPort");
        wsdl11Definition.setLocationUri("/soa");
        wsdl11Definition.setTargetNamespace("http://localhost/soa/search");
        wsdl11Definition.setSchema(searchschema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema searchShema() {
        return new SimpleXsdSchema(new ClassPathResource("search.xsd"));
    }
}