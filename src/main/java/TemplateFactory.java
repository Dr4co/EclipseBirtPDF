import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

public class TemplateFactory {

    private VelocityEngine velocityEngine;

    public VelocityEngine get() {
        if (velocityEngine == null) {
            try {
                Properties velocityConfig = new Properties();
                velocityConfig.put("resource.loader", "class");
                velocityConfig.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
                velocityConfig.put("class.resource.loader.description", "Load resources from the CLASSPATH");
                velocityEngine = new VelocityEngine(velocityConfig);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return velocityEngine;
    }
}
