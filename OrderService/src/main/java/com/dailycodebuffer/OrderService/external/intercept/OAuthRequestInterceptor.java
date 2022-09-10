package com.dailycodebuffer.OrderService.external.intercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class OAuthRequestInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer "
        + oAuth2AuthorizedClientManager
                .authorize(OAuth2AuthorizeRequest
                        .withClientRegistrationId("internal-client")
                        .principal("internal")
                        .build())
                .getAccessToken().getTokenValue());
    }
}
