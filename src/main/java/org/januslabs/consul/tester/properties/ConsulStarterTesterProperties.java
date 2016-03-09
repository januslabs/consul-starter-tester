package org.januslabs.consul.tester.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * Sample Properties for your application
 * performs lookup from consul for name value and
 * looks up vault for secrets
 */
@Data
@Component
public class ConsulStarterTesterProperties {

  @Value("#{#vault(vaultClient,systemEnvironment['WEB_ODS_PASSWD'])}")
  private String password;
  @Value("#{#consul(consulClient,systemEnvironment['CAS'])}")
  private String name;
}
