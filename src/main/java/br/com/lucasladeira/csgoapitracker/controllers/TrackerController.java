package br.com.lucasladeira.csgoapitracker.controllers;

import br.com.lucasladeira.csgoapitracker.client.TrackerClient;
import br.com.lucasladeira.csgoapitracker.response.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webclient")
public class TrackerController {

    @Autowired
    private TrackerClient trackerClient;

    @GetMapping("/{nickname}")
    public Mono<Tracker> getPlayersStatsByNickname(@PathVariable(name = "nickname") String nickname){
        return trackerClient.getPlayerStatsByNick(nickname);
    }

}
