package org.sc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.sc.gateway.rule.NacosMetadataAwareRule;

@SpringBootApplication
public class GatewayApplication {
	//@Bean
	public NacosMetadataAwareRule metadataAwareRuleNacos() {
		NacosMetadataAwareRule metadataAwareRule = new NacosMetadataAwareRule();
		return metadataAwareRule;
	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
