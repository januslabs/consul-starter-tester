package org.januslabs.consul.tester.main;

import org.januslabs.consul.tester.util.VaultSpelFunctionProvider;
import org.januslabs.consul.tester.util.VaultSpelFunctions;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.januslabs.consul.tester")
@EnableConfigurationProperties
public class ConsulStarterSampleApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ConsulStarterSampleApplication.class);
  }

  public static void main(String[] args) {
    new ConsulStarterSampleApplication()
        .configure(new SpringApplicationBuilder(ConsulStarterSampleApplication.class)).run(args);
  }

  @Bean
  public VaultSpelFunctionProvider vaultSpelFunctionProvider() {
    return new VaultSpelFunctionProvider(VaultSpelFunctions.class);
  }

  
}
