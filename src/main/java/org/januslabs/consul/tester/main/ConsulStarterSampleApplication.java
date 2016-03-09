package org.januslabs.consul.tester.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.januslabs.consul.tester")
public class ConsulStarterSampleApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ConsulStarterSampleApplication.class);
  }

  public static void main(String[] args) {
    new ConsulStarterSampleApplication()
        .configure(new SpringApplicationBuilder(ConsulStarterSampleApplication.class)).run(args);
  }
}
