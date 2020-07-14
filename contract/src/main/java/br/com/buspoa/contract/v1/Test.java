package br.com.buspoa.contract.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/v1/test")
@AllArgsConstructor
public class Test {

    @GetMapping
    public String teste() {
        return "TEST . . .";
    }

    @GetMapping("/listLines")
    public ResponseEntity<String> listLines () {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
        ResponseEntity<String> reponse = restTemplate.getForEntity(url, String.class);
        return reponse;
    }

    @GetMapping("/listItinerary")
    public ResponseEntity<String> listItineraryByCodeLine(String codeLine) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
        ResponseEntity<String> reponse = restTemplate.getForEntity(url + codeLine, String.class);
        return reponse;
    }
}
