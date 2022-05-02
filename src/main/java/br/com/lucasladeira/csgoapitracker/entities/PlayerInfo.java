package br.com.lucasladeira.csgoapitracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerInfo {

    private String steamId;
    private String steamNickname;
    private String avatarUrl;

}
