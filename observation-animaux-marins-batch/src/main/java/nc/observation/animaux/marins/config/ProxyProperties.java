package nc.observation.animaux.marins.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe contenant les propriétés du proxy
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "application.proxy")
public class ProxyProperties {

    private boolean use;

    private String host;

    private Integer port;
}
