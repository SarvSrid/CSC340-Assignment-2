package com.csc340f23.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getWeather();
        System.exit(0);
    }

    public static void getWeather() {
        try {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=Greensboro&units=metric&appid=4f330c4a44ecb11f98f70f9bcdcb4b6f";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonWeather = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonWeather);

            //Making root paths
            JsonNode wind = root.path("wind");
            JsonNode main = root.path("main");

            //Setting the root paths to call JSON data and setting them as primitive data types
            String cityName = root.path("name").asText();
            double windSpeed = wind.path("speed").asDouble();
            double currentTemp = main.path("feels_like").asDouble();
            double maxTemp = main.path("temp_max").asDouble();
            double minTemp = main.path("temp_min").asDouble();

            //Printing out information from API
            System.out.println("City: " + cityName);
            System.out.println("Max Temperature: " + maxTemp + "°C");
            System.out.println("Min Temperature: " + minTemp + "°C");
            System.out.println("Current Temperature Outside: " + currentTemp);
            System.out.println("Wind Speed: " + windSpeed);


        } catch (JsonProcessingException ex) {
            System.out.println("error in getWeather");
        }
    }

}
