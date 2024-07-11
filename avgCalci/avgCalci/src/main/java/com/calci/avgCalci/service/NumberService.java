package com.calci.avgCalci.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class NumberService {
    private static final int WINDOW_SIZE = 10;
    private static final Map<String, List<Integer>> windows = new HashMap<>();
    static {
        windows.put("p", new ArrayList<>());
        windows.put("f", new ArrayList<>());
        windows.put("e", new ArrayList<>());
        windows.put("r", new ArrayList<>());
    }
    private final WebClient webClient;

    @Value("${api.auth.token}")
    private String authToken;

    public NumberService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://20.244.56.144/test").build();
    }
    private double calculateAverage(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
    private List<Integer> fetchNumbers(String type) {
            String url = String.format("/%s", switch (type) {
                case "p" -> "primes";
                case "f" -> "fibonacci";
                case "e" -> "even";
                case "r" -> "rand";
                default -> throw new IllegalArgumentException("Invalid type: " + type);
            })