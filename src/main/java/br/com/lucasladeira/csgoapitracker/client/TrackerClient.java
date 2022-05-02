package br.com.lucasladeira.csgoapitracker.client;

import br.com.lucasladeira.csgoapitracker.response.Tracker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TrackerClient {

    private final WebClient webClient;

    @Value("${client.api.header_name}")
    private String HEADER_NAME;

    @Value("${client.api.header_value}")
    private String HEADER_VALUE;

    public TrackerClient(WebClient.Builder builder) {
        webClient = builder
                .baseUrl("https://public-api.tracker.gg/v2/csgo/standard/profile/steam/")
                .build();
    }

    public Mono<Tracker> getPlayerStatsByNick(String nickname){
        return webClient
                .get()
                .uri(nickname)
                .header(HEADER_NAME, HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Jogador não encontrado!"))
                )
                .bodyToMono(Tracker.class);

    }
}
