package com.spring.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.server.environment.JGitEnvironmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private JGitEnvironmentRepository jGitEnvironmentRepository;
	
	@Value("${spring.cloud.config.server.git.refresh-rate}")
	private int refreshRate;
	
	@GetMapping ("/update")
	public void updateProps() throws Exception {
		logger.info("Updating the properties");
		
		jGitEnvironmentRepository.setRefreshRate(0);
		String refresh = jGitEnvironmentRepository.refresh("master");
		logger.info("refresh {}", refresh);
		jGitEnvironmentRepository.setRefreshRate(refreshRate);
	}
}

