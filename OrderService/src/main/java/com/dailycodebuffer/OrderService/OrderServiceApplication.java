package com.dailycodebuffer.OrderService;

import com.dailycodebuffer.OrderService.external.intercept.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate
				= new RestTemplate();
		restTemplate.setInterceptors(
				Arrays.asList(
						new RestTemplateInterceptor(
								clientManager(clientRegistrationRepository
										,oAuth2AuthorizedClientRepository))));
		return restTemplate;
	}

	@Bean
	public OAuth2AuthorizedClientManager clientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
	) {
		OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider
				= OAuth2AuthorizedClientProviderBuilder
				.builder()
				.clientCredentials()
				.build();

		DefaultOAuth2AuthorizedClientManager oAuth2AuthorizedClientManager
				= new DefaultOAuth2AuthorizedClientManager(
						clientRegistrationRepository,
				oAuth2AuthorizedClientRepository);

		oAuth2AuthorizedClientManager.setAuthorizedClientProvider(
				oAuth2AuthorizedClientProvider
		);

		return oAuth2AuthorizedClientManager;
	}

}
