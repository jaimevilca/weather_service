package com.example;

import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;
import io.test.WeatherRequest;
import io.test.WeatherResponse;
import io.test.WeatherService;

@GrpcService
public class WeatherGrpcService implements WeatherService {

    @Override
    public Uni<WeatherResponse> getWeather(WeatherRequest request) {
        System.out.println("Request received from client:\n" + request);

        String weatherPredicted = "Rainy";
        if (startWithVowel(request.getCity())) {
            weatherPredicted = "Sunny";
        }

        WeatherResponse response = WeatherResponse.newBuilder()
                .setCity(request.getCity())
                .setWeather(weatherPredicted)
                .build();

        return Uni.createFrom().item(response);
    }

    public boolean startWithVowel(String city) {
        return "aeiou".indexOf(Character.toLowerCase(city.charAt(0))) != -1;
    }
}
