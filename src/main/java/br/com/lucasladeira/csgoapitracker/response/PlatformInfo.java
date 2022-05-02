package br.com.lucasladeira.csgoapitracker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlatformInfo implements Serializable {

    private String platformUserId;
    private String platformUserHandle;
    private String avatarUrl;

}
