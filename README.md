# consul-starter-tester
===========================
Sample WAR Application for testing the consul-starter, deploy the application to external tomcat container to test it.


Required Frameworks
===================
Spring boot
Jersey Jax-RS
Consul-Client

Installation
=============

The easiest way to get started with this starter project is to fork, clone or download from the link below.

git clone https://github.com/januslabs/consul-starter-tester.git 

You will also need to install Consul. This starter project assumes that you have a basic understanding of Consul's operations.

https://www.consul.io/intro/getting-started/install.html



Building
==========
Java Version: 1.8

Basic Compile and Test
======================
$ mvn clean install

###Maven:

```xml

<dependencies>
    <dependency>
     	<groupId>org.januslabs</groupId>
		<artifactId>consul-starter</artifactId>
		<version>0.0.1</version>
    </dependency>
    <!-- include spring boot,Jersey, Orbitzworldwide consul client -->
</dependencies>

<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>
```
Configuration
=============


consul.url : http://localhost:8500
consul.enabled=true
spring.application.name=consul-starter-tester
server.port=8080
server.context-path=/consul-starter-tester
spring.jersey.application-path=/apis





