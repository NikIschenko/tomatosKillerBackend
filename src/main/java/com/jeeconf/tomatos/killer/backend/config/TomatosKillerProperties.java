package com.jeeconf.tomatos.killer.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "tomatos.killer")
public class TomatosKillerProperties {
	private String password;
}