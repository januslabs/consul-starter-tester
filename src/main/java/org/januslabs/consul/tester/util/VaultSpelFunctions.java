package org.januslabs.consul.tester.util;

import java.util.function.Consumer;

import org.januslabs.vault.VaultClient;

import com.orbitz.consul.Consul;


public class VaultSpelFunctions {
  @FunctionalInterface
  public interface VaultSpelFunction extends Consumer<String> {
  }

  public static String vault(VaultClient vaultclient, String key) {
    System.out.println("VaultClient: " + vaultclient);
    System.out.println("Key: " + key);
    return vaultclient.getValue(key);
  }
  
  public static String consul(Consul consulClient, String key) {
    System.out.println("ConsulClient: " + consulClient);
    System.out.println("Key: " + key);
    return consulClient.keyValueClient().getValueAsString(key).get();
  }
}
