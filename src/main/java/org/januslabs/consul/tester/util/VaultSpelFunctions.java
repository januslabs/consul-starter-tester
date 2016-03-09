package org.januslabs.consul.tester.util;

import java.util.Arrays;
import java.util.function.Consumer;

import javax.sql.DataSource;

import org.januslabs.vault.VaultClient;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import com.orbitz.consul.Consul;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VaultSpelFunctions {
  @FunctionalInterface
  public interface  VaultSpelFunction<T> extends Consumer<T> {
  }

  public static String vault(VaultClient vaultclient, String key) {
    log.info("VaultClient {} " , vaultclient);
    log.info("Key {}" , key);
    return vaultclient.getValue(key);
  }
  
  public static String consul(Consul consulClient, String key) {
    log.info("Consul {} " , consulClient);
    log.info("Key {}" , key);
    return consulClient.keyValueClient().getValueAsString(key).get();
  }
  /*
   * 
<Resource name="tpm/datasource"
auth="Container" factory="org.apache.tomcat.jdbc.pool.DataSourceFactory" jmxEnabled="true"
type="javax.sql.DataSource"
username="TRDPRTN_TOMCAT" password="Tctrdaeb"
driverClassName="oracle.jdbc.driver.OracleDriver"
url="jdbc:oracle:thin:@ldap://tnsnames.assurant.com:389/V3${db.env}1ENRL,cn=OracleContext,dc=act,dc=assurant,dc=com"
testWhileIdle="true"
              testOnBorrow="true"
              testOnReturn="false"
              validationQuery="SELECT 1 from dual"
              validationInterval="30000"
              timeBetweenEvictionRunsMillis="30000"
              maxActive="20"
              minIdle="0"
              maxIdle="2"
              maxWait="10000"
              initialSize="2"
              removeAbandonedTimeout="300"
              removeAbandoned="true"
              logAbandoned="true"
              minEvictableIdleTimeMillis="30000" jdbcInterceptors=
"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState(autoCommit=false,readOnly=true);
org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;SlowQueryReport(threshold=2000);
SlowQueryReportJmx(threshold=2000);org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer" />

   */
  public static DataSource datasource(Consul consulClient, VaultClient vaultclient,String key) {
    log.info("Consul {} " , consulClient);
    log.info("VaultClient {} " , vaultclient);
    log.info("Key {}" , key);
    String [] configValues=consulClient.keyValueClient().getValueAsString(key).get().split("[+]");
    Arrays.asList(configValues).forEach(n -> log.info(n));
    log.info("Constructing datasource {}");
    DataSource datasource= DataSourceBuilder.create()
    .type(HikariDataSource.class)
    .url(configValues[0])
    .username(configValues[1])
    .password(vaultclient.getValue(configValues[2]))
    .build();
    log.info("Constructed datasource {}", datasource);
    return datasource;
  }
  
}
