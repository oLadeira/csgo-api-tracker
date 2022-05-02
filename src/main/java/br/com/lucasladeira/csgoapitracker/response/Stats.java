package br.com.lucasladeira.csgoapitracker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stats implements Serializable {

    private TimePlayed timePlayed;
    private Kills kills;
    private Deaths deaths;
    private Headshots headshots;
    private Mvp mvp;
    private Wins wins;
    private Losses losses;
    private MatchesPlayed matchesPlayed;

}
