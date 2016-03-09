package org.januslabs.consul.tester.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.januslabs.consul.tester.endpoints.SampleEndpoint;
import org.springframework.stereotype.Component;

@Component
public class ConsulSampleJerseyConfig extends ResourceConfig {

  public ConsulSampleJerseyConfig() {
    register(SampleEndpoint.class);
  }
}
