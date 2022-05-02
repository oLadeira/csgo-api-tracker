package br.com.lucasladeira.csgoapitracker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Data implements Serializable {

    private PlatformInfo platformInfo;

    private List<Segments> segments;

}
