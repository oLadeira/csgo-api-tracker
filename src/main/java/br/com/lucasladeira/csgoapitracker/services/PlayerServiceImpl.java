package br.com.lucasladeira.csgoapitracker.services;

import br.com.lucasladeira.csgoapitracker.client.TrackerClient;
import br.com.lucasladeira.csgoapitracker.entities.GameInfo;
import br.com.lucasladeira.csgoapitracker.entities.PlayerInfo;
import br.com.lucasladeira.csgoapitracker.entities.PlayerStats;
import br.com.lucasladeira.csgoapitracker.response.Tracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private TrackerClient trackerClient;

    DecimalFormat formatter = new DecimalFormat("0.00");

    /*
        Cálculo de KD = Kills / Deaths
        Cálculo de Headshot percentage = Headshots / Kills
        Cálculo de horas jogadas = minutos * 60 * 60 (pesquisar alguma maneira mais inteligente, alguma lib)
    */

    @Override
    public PlayerStats getPlayerInfoByUsername(String username) {
        Mono<Tracker> playerTracker = trackerClient.getPlayerStatsByNick(username);

        PlayerStats player = fromMonoTracker(playerTracker);

        //chamando funcao de calcular kd
        player.getGameInfo().setKd(
                calculatePlayerKd(player.getGameInfo().getKills(), player.getGameInfo().getDeaths())
        );

        //chamando funcao de calcular horas jogadas
        player.getGameInfo().setTimePlayed(
                calculateHoursPlayed(player.getGameInfo().getTimePlayed())
        );

        //chamando funcao de calcular porcentagem de headshots
        player.getGameInfo().setHeadshotsPercentage(
                calculateHeadshotPercentage(player.getGameInfo().getKills(), player.getGameInfo().getHeadshots())
        );
        return player;
    }


    @Override
    public Double calculatePlayerKd(Integer kills, Integer deaths) {
        Double kdCalc = Double.valueOf(kills) / Double.valueOf(deaths);
        String kdFormatted = formatter.format(kdCalc);
        return Double.valueOf(kdFormatted.replace(",", "."));
    }

    @Override
    public Integer calculateHoursPlayed(Integer minutes) {
        String hours = String.valueOf(TimeUnit.SECONDS.toHours(minutes));
        return Integer.valueOf(hours);
    }

    @Override
    public Double calculateHeadshotPercentage(Integer totalKills, Integer totalHeadshots) {
        Double calc = (Double.valueOf(totalHeadshots) / Double.valueOf(totalKills)) * 100;
        String calcFormatted = formatter.format(calc);
        return Double.valueOf(calcFormatted.replace(",", "."));
    }

    @Override
    public PlayerStats fromMonoTracker(Mono<Tracker> monoTracker) {
        PlayerInfo playerInfo = new PlayerInfo();
        GameInfo gameInfo = new GameInfo();

        //forçar o fluxo esperar a resposta chegar
        Tracker tracker = monoTracker.block();

        playerInfo.setSteamNickname(tracker.getData().getPlatformInfo().getPlatformUserHandle());
        playerInfo.setAvatarUrl(tracker.getData().getPlatformInfo().getAvatarUrl());
        playerInfo.setSteamId(tracker.getData().getPlatformInfo().getPlatformUserId());

        tracker.getData().getSegments().forEach(segments -> {
            gameInfo.setWins(segments.getStats().getWins().getValue());
            gameInfo.setMvp(segments.getStats().getMvp().getValue());
            gameInfo.setLosses(segments.getStats().getLosses().getValue());
            gameInfo.setDeaths(segments.getStats().getDeaths().getValue()); //
            gameInfo.setKills(segments.getStats().getKills().getValue()); //
            gameInfo.setMatchesPlayed(segments.getStats().getMatchesPlayed().getValue());
            gameInfo.setHeadshots(segments.getStats().getHeadshots().getValue());
            gameInfo.setTimePlayed(segments.getStats().getTimePlayed().getValue());
        });

        PlayerStats playerStats = new PlayerStats();
        playerStats.setPlayerInfo(playerInfo);
        playerStats.setGameInfo(gameInfo);
        return playerStats;
    }
}
