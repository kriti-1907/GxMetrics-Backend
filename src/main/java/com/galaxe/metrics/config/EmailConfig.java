package com.galaxe.metrics.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	@Bean
public JavaMailSender javaMailSender() {
	JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
	javaMailSender.setHost("smtp.gmail.com");
	javaMailSender.setPort(587);
	javaMailSender.setUsername("agarkriti19@gmail.com");
	javaMailSender.setPassword("owqnojldktlkltso");
	
	Properties properties=javaMailSender.getJavaMailProperties();
	properties.put("mail.transport.protocol", "smtp");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.transport.protocol", "smtp");
	
	return javaMailSender;
	
}
}
