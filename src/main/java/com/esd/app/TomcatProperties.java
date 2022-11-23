package com.esd.app;

import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatProperties implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers(
                new TomcatContextCustomizer() {
                    @Override
                    public void customize(Context context) {
                        context.setReloadable(true);
                    }
                }
        );
    }
}
