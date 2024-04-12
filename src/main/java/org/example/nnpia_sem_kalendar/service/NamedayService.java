package org.example.nnpia_sem_kalendar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public class NamedayService {
    private final String apiUrl = "https://nameday.abalin.net/api/V1/getname";

    public LocalDate getHolidayDateByName(String name) throws ParseException, JsonProcessingException, UnsupportedEncodingException {
        String country = "cz";
        int year = LocalDate.now().getYear();

        String encodedName = URLEncoder.encode(name, "UTF-8");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("name", encodedName)
                .queryParam("country", country);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode namedaysNode = rootNode.get("0");
            if (namedaysNode != null && namedaysNode.isArray() && namedaysNode.size() > 0) {
                JsonNode firstNameday = namedaysNode.get(0);
                int day = firstNameday.get("day").asInt();
                int month = firstNameday.get("month").asInt();
                return LocalDate.of(year, month, day); // Zde přidáme rok, měsíc a den ze zprávy JSON
            }
        }

        return null;
    }
}
