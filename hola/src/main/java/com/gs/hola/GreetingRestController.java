package com.gs.hola;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "greeting")
public class GreetingRestController {
    private RestTemplate restTemplate = new RestTemplate();
    private String saying;
    private String backendServiceHost;
    private int backendServicePort;

    @GetMapping(value = "/greeting", produces = "text/plain")
    public String greeting() {
        String backendServiceUrl = String.format("http://%s:%d/api/backend?greeting={greeting}", backendServiceHost, backendServicePort);
        System.out.println("Sending to: " + backendServiceUrl);
        BackendDTO backendDTOResponse = restTemplate.getForObject(backendServiceUrl, BackendDTO.class, saying);
        System.out.println(backendDTOResponse);
        if (backendDTOResponse != null) {
            return backendDTOResponse.getGreeting() + " at host: " + backendDTOResponse.getIpAddress();
        }
        return "";
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getBackendServiceHost() {
        return backendServiceHost;
    }

    public void setBackendServiceHost(String backendServiceHost) {
        this.backendServiceHost = backendServiceHost;
    }

    public int getBackendServicePort() {
        return backendServicePort;
    }

    public void setBackendServicePort(int backendServicePort) {
        this.backendServicePort = backendServicePort;
    }
}
