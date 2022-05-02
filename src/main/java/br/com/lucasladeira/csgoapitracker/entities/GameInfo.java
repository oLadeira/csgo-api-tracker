package br.com.lucasladeira.csgoapitracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameInfo {

    private Integer timePlayed;
    private Integer kills;
    private Integer deaths;
    private Double kd; //
    private Integer headshots;
    private Double headshotsPercentage; //
    private Integer mvp;
    private Integer wins;
    private Integer losses;
    private Integer matchesPlayed;
}
