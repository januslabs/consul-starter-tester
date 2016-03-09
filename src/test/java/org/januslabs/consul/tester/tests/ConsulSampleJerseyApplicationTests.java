package org.januslabs.consul.tester.tests;

import org.januslabs.consul.tester.main.ConsulStarterSampleApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ConsulStarterSampleApplication.class)
@WebIntegrationTest(randomPort = true)
public class ConsulSampleJerseyApplicationTests {

  @Value("${local.server.port}")
  private int port;
  @Value("${server.context-path}")
  private String contextRoot;
  @Value("${spring.jersey.application-path}")
  private String jerseycontextRoot;

  private RestTemplate restTemplate = new TestRestTemplate();

  @Test
  public void contextLoads() {
    ResponseEntity<String> entity = this.restTemplate.getForEntity(
        "http://localhost:" + this.port + this.contextRoot + this.jerseycontextRoot + "/hello",
        String.class);
    Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void health() {
    ResponseEntity<String> entity = this.restTemplate
        .getForEntity("http://localhost:" + this.port + this.contextRoot + "/health", String.class);
    Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);
  }
}
