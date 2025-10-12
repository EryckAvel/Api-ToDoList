package com.eryckavel.todolist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Startup {

	private static final Logger logger = LoggerFactory.getLogger(Startup.class);

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ConfigurableApplicationContext context = SpringApplication.run(Startup.class, args);
		WebServerApplicationContext webContext = (WebServerApplicationContext) context;
		String version = context.getEnvironment().getProperty("api.version", "N/A");
		int port = webContext.getWebServer().getPort();
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		String profiles = activeProfiles.length == 0 ? "default" : String.join(", ", activeProfiles);
		long elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("══════════════════════════════════════════════");
		logger.info("✅ Aplicação inicializada com sucesso!");
		logger.info("📌 Versão da API: {}", version);
		logger.info("📦 Porta do servidor: {}", port);
		logger.info("🌱 Perfil ativo(s): {}", profiles);
		logger.info("⏱ Tempo de inicialização: {} ms", elapsedTime);
		logger.info("══════════════════════════════════════════════");
	}

}
