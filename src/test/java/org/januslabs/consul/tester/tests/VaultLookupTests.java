package org.januslabs.consul.tester.tests;

import org.januslabs.consul.tester.main.ConsulStarterSampleApplication;
import org.januslabs.consul.tester.properties.ConsulStarterTesterProperties;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ConsulStarterSampleApplication.class)
@IntegrationTest
public class VaultLookupTests {

  /*
   * Root Token from the dev server
   */
  public static String VAULT_TOKEN = "67f8dd40-3d8c-a18a-c4cc-c6dc58c7993c";
  public @Autowired ConsulStarterTesterProperties myAppProperties;
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
    Assert.hasText(myAppProperties.getPassword(), "A33ur@nt");
    Assert.hasText(myAppProperties.getName(), "test");
  }
}
