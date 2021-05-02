package Data;

import Config.Config;

import java.util.ArrayList;
import java.util.List;

public class FantasyData {
    public boolean hasAnnouncedData;
    public boolean hasCompleteData;
    public List<Player> wkPlayer;
    public List<Player> batPlayer;
    public List<Player> allPlayer;
    public List<Player> bowlPlayer;

    public FantasyData(List<Player> wkPlayer, List<Player> batPlayer, List<Player> allPlayer, List<Player> bowlPlayer) {
        this.wkPlayer = wkPlayer;
        this.batPlayer = batPlayer;
        this.allPlayer = allPlayer;
        this.bowlPlayer = bowlPlayer;
        this.hasAnnouncedData = false;
        this.hasCompleteData = false;
        init();
    }

    public void init() {
        estimateParameter();
        estimateParameterForDNPPlayers();
    }

    public void estimateParameterForDNPPlayers() {
        estimateParameterForDNPPlayers(filterOutUnannouncedPlayers(this.wkPlayer));
        estimateParameterForDNPPlayers(filterOutUnannouncedPlayers(this.batPlayer));
        estimateParameterForDNPPlayers(filterOutUnannouncedPlayers(this.allPlayer));
        estimateParameterForDNPPlayers(filterOutUnannouncedPlayers(this.bowlPlayer));
    }

    public List<Player> filterOutUnannouncedPlayers(List<Player> players) {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            if (player.playerAnnounced.equals("⬤ Announced") || player.playedLastMatch) {
                result.add(player);
            }
        }
        return result;
    }


    public void estimateParameterForDNPPlayers(List<Player> players) {
        double minDist, dist;
        Player p = null;
        List<Player> sufficientDataPlayer = new ArrayList<>();
        for (Player player : players) {
            if (player.getNoNDNPData().size() > Config.indVariableCount)
                sufficientDataPlayer.add(player);
        }

        for (Player insuffiicientDataPlayers : players) {
            if (insuffiicientDataPlayers.getNoNDNPData().size() <= Config.indVariableCount) {
                minDist = 1000000000.0d;

                for (Player player : sufficientDataPlayer) {
                    dist = Math.abs(insuffiicientDataPlayers.getSel() - player.getSel());
                    if (Double.compare(minDist, dist) == 1) {
                        p = player;
                        minDist = dist;
                    }
                }
                try {
                    if (p != null) {
                        insuffiicientDataPlayers.accuracy = p.accuracy;
                        insuffiicientDataPlayers.parameters = p.parameters.clone();
                    }
                } catch (Exception e) {
                    int a = 1;
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