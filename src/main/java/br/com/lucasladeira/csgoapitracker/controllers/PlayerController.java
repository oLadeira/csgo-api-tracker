package br.com.lucasladeira.csgoapitracker.controllers;

import br.com.lucasladeira.csgoapitracker.entities.PlayerStats;
import br.com.lucasladeira.csgoapitracker.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker")
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;


    @GetMapping("/{username}")
    public ResponseEntity<PlayerStats> getPlayerInfoByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerInfoByUsername(username));
    }
}
