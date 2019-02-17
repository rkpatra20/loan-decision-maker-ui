package com.lendme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class LdmUIController {

    private static final Logger LOGGER=LoggerFactory.getLogger(LdmUIController.class);
    @Autowired
    private LdmScoreServiceClient ldmScoreServiceClient;
    @PostMapping("/score")
    @ResponseBody
    public String getLdmResponse(@RequestBody Map<String, String> inputs) {


        return ldmScoreServiceClient.getScore(inputs);

    }


    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }
}


@Component
class LdmScoreServiceClient {
    @Value("${ldm.score.url}")
    private String url;


    public String getScore(Map<String, String> inputs) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(url, inputs, String.class).getBody();
    }

}
