package Data;

import Config.Config;

import java.util.ArrayList;
import java.util.List;

public class FantasyData {
    public List<Player> wkPlayer;
    public List<Player> batPlayer;
    public List<Player> allPlayer;
    public List<Player> bowlPlayer;

    public FantasyData(List<Player> wkPlayer, List<Player> batPlayer, List<Player> allPlayer, List<Player> bowlPlayer) {
        this.wkPlayer = wkPlayer;
        this.batPlayer = batPlayer;
        this.allPlayer = allPlayer;
        this.bowlPlayer = bowlPlayer;
        estimateParameterForDNPPlayers();
    }

    public void estimateParameterForDNPPlayers() {
        estimateParameterForDNPPlayers(this.wkPlayer);
        estimateParameterForDNPPlayers(this.batPlayer);
        estimateParameterForDNPPlayers(this.allPlayer);
        estimateParameterForDNPPlayers(this.bowlPlayer);
    }

    public void filterOutUnannouncedPlayers() {
        this.wkPlayer = filterOutUnannouncedPlayers(this.wkPlayer);
        this.batPlayer = filterOutUnannouncedPlayers(this.batPlayer);
        this.allPlayer = filterOutUnannouncedPlayers(this.allPlayer);
        this.bowlPlayer = filterOutUnannouncedPlayers(this.bowlPlayer);
    }

    private List<Player> filterOutUnannouncedPlayers(List<Player> players) {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            if (player.playerAnnounced.equals("⬤ Announced")) {
                result.add(player);
            }
//            if (player.playedLastMatch) {
//                result.add(player);
//            }
        }
        return result;
    }


    public void estimateParameterForDNPPlayers(List<Player> players) {
        double minDist, dist;
        Player p = null;
        List<Player> sufficientDataPlayer = new ArrayList<>();
        for (Player player : players) {
            if (player.playerStats.size() > Config.indVariableCount)
                sufficientDataPlayer.add(player);
        }

        for (Player insuffiicientDataPlayers : players) {
            if (insuffiicientDataPlayers.playerStats.size() <= Config.indVariableCount) {
                minDist = 1000000000.0d;

                for (Player player : sufficientDataPlayer) {
                    dist = Math.abs(insuffiicientDataPlayers.getSel() - player.getSel());
                    if (Double.compare(minDist, dist) == 1) {
                        p = player;
                        minDist = dist;
                    }
                }
                if (p != null) {
                    insuffiicientDataPlayers.accuracy = p.accuracy;
                    insuffiicientDataPlayers.parameters = p.parameters.clone();
                }
            }
        }
    }

    public void estimateParameter() {
        for (Player player : wkPlayer) {
            player.estimateParameters();
        }
        for (Player player : batPlayer) {
            player.estimateParameters();
        }
        for (Player player : allPlayer) {
            player.estimateParameters();
        }
        for (Player player : bowlPlayer) {
            player.estimateParameters();
        }
    }
}