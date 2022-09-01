package com.springframework.saiedmadminpanel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SaiEdmAdminpanelApplication {
    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> jsonToSend = new HashMap<>();
//        jsonToSend.put("name", "test name");
//        jsonToSend.put("name2", "test name2");
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
//        String url = "https://reqres.in/api/users/";
//
//        String response = restTemplate.postForObject(url, request, String.class);
//        System.out.println(response);

        SpringApplication.run(SaiEdmAdminpanelApplication.class, args);
    }

}
