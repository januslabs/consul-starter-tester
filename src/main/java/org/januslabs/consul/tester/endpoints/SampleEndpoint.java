package org.januslabs.consul.tester.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.januslabs.consul.tester.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class SampleEndpoint {

  private @Autowired Service service;

  public SampleEndpoint(Service service) {
    this.service = service;
  }

  public SampleEndpoint() {

  }

  @GET
  public String message() {
    return "Hello " + this.service.message();
  }
}
