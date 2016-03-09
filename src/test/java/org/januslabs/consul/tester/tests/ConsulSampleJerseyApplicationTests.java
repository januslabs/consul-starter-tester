package org.januslabs.consul.tester.tests;

import org.januslabs.consul.tester.main.ConsulStarterSampleApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
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
  public static String VAULT_TOKEN = "67f8dd40-3d8c-a18a-c4cc-c6dc58c7993c";
  
  static
  {
    
    final EnvironmentVariables environmentVariables = new EnvironmentVariables();
    System.setProperty("WEB_ODS_PASSWD", "webods/password");
    environmentVariables.set("VAULT_TOKEN", VAULT_TOKEN);
    environmentVariables.set("VAULT_ADDR", "http://127.0.0.1:8200");
    environmentVariables.set("WEB_ODS_PASSWD", "webods/password");
    environmentVariables.set("CAS", "CASURL");
  }

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
