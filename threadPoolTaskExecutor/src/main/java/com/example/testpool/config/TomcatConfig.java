package com.example.testpool.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Connector.class})
public class TomcatConfig {

   @Bean
    public TomcatServletWebServerFactory createEmbeddedServletContainerFactory() {
       TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
       //增加连接器的定制配置
       serverFactory.addConnectorCustomizers(connector->{
           Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
           //定制keepAliveTimeout，确定下次请求过来之前Socket的连接保持时间
           protocolHandler.setKeepAliveTimeout(600000);//600秒

           //客户端发送请求超过10000个时强制关闭Socket连接
           protocolHandler.setMaxKeepAliveRequests(10000);

           //设置最大连接数
           protocolHandler.setMaxConnections(3000);
           //其他配置 ...

       });
       return serverFactory;
   }


}
