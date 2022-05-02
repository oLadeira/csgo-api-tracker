package br.com.lucasladeira.csgoapitracker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerStats {

    private PlayerInfo playerInfo;

    private GameInfo gameInfo;
}
