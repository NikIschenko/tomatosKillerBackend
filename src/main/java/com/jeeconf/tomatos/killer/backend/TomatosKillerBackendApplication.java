package com.jeeconf.tomatos.killer.backend;

import com.jeeconf.tomatos.killer.backend.config.TomatosKillerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@SpringBootApplication
public class TomatosKillerBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TomatosKillerBackendApplication.class, args);
	}

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final TomatosKillerProperties tomatosKillerProperties;

	@Autowired
	public TomatosKillerBackendApplication(SimpMessagingTemplate simpMessagingTemplate, TomatosKillerProperties tomatosKillerProperties) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.tomatosKillerProperties = tomatosKillerProperties;
	}

	@ResponseBody
	@GetMapping(path = "/start/{password}")
	public void laserStart(@PathVariable(value="password") String password) throws AccessDeniedException {
		if (tomatosKillerProperties.getPassword().equalsIgnoreCase(password)) {
			simpMessagingTemplate.convertAndSend("/laser", "on");
		} else {
			throw new AccessDeniedException("Incorrect password");
		}
	}

	@ResponseBody
	@GetMapping(path = "/stop/{password}")
	public void laserStop(@PathVariable(value="password") String password) throws AccessDeniedException {
		if (tomatosKillerProperties.getPassword().equalsIgnoreCase(password)){
			simpMessagingTemplate.convertAndSend("/laser", "off");
		} else {
			throw new AccessDeniedException("Incorrect password");
		}
	}
}
