package br.com.lucasladeira.csgoapitracker.services;

import br.com.lucasladeira.csgoapitracker.entities.PlayerStats;
import br.com.lucasladeira.csgoapitracker.response.Tracker;
import reactor.core.publisher.Mono;

public interface PlayerService {

    PlayerStats getPlayerInfoByUsername(String username);

    PlayerStats fromMonoTracker(Mono<Tracker> tracker);

    Double calculatePlayerKd(Integer kills, Integer deaths);

    Integer calculateHoursPlayed(Integer minutes);

    Double calculateHeadshotPercentage(Integer totalKills, Integer totalHeadshots);
}
