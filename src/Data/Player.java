package Data;

import Config.Config;
import Util.DateUtil;
import Util.RegressionUtil;

import java.util.Calendar;
import java.util.List;

public class Player {
    public String playerName;
    public String playerTeamName;
    public String playerAnnounced;
    public String playerCredit;
    public String playerSel;
    public double[] parameters;
    public double accuracy;
    public double pointMean;
    public List<PlayerStats> playerStats;


    public Player(String playerName,
                  String playerTeamName,
                  String playerAnnounced,
                  String playerCredit,
                  String playerSel,
                  List<PlayerStats> playerStats) {
        this.playerName = playerName;
        this.playerTeamName = playerTeamName;
        this.playerAnnounced = playerAnnounced;
        this.playerCredit = playerCredit;
        this.playerSel = playerSel;
        this.playerStats = playerStats;
        this.parameters = null;
        this.accuracy = -1.0d;
        this.pointMean = 0.0d;
        calculatePointMean();
        estimateParameters();
        int a = 1;
    }

    public Player(String playerName,
                  String playerTeamName,
                  String playerAnnounced,
                  String playerCredit,
                  String playerSel,
                  double[] parameters,
                  double accuracy,
                  double pointMean,
                  List<PlayerStats> playerStats) {
        this.parameters = parameters;
        this.accuracy = accuracy;
        this.playerName = playerName;
        this.playerTeamName = playerTeamName;
        this.playerAnnounced = playerAnnounced;
        this.playerCredit = playerCredit;
        this.playerSel = playerSel;
        this.playerStats = playerStats;
        this.pointMean = pointMean;
        calculatePointMean();
        estimateParameters();
        int a=1;
    }

    private double[][] getXStats() {
        double[][] res = new double[Config.indVariableCount][playerStats.size()];

        int indVarCount = 0;
        //variable sel
        if (Config.indVariableCount >= 1) {
            for (int i = 0; i < playerStats.size(); i++) {
                res[indVarCount][i] = playerStats.get(i).getSel();
            }
            indVarCount++;
        }

        if (Config.indVariableCount >= 2) {
            //variable date
            for (int i = 0; i < playerStats.size(); i++) {
                res[indVarCount][i] = DateUtil.getDiffBetween(playerStats.get(i).date,
                        playerStats.get(playerStats.size() - 1).date);
            }
            indVarCount++;
        }

        if (Config.indVariableCount >= 3) {
            //variable date
            for (int i = 0; i < playerStats.size(); i++) {
                res[indVarCount][i] = playerStats.get(i).getCredit();
            }
            indVarCount++;
        }

        return res;
    }

    private double[][] getYStats() {
        double[][] res = new double[playerStats.size()][1];
        for (int i = 0; i < playerStats.size(); i++) {
            res[i][0] = playerStats.get(i).getPoint();
        }
        return res;
    }

    public double getSel() {
        return Double.parseDouble(playerSel.substring(7).replaceAll("%", ""));
    }

    public double getCredit() {
        return Double.parseDouble(playerCredit);
    }

    public double getMatchDayNo() {
        return DateUtil.getDiffBetween(Calendar.getInstance().getTime(), DateUtil.parseDate(playerStats.get(playerStats.size() - 1).date));
    }

    public void estimateParameters() {
        if (playerStats.size() >= (Config.indVariableCount + 1)) {
            parameters = RegressionUtil.estimateParameters(getXStats(), getYStats(), Config.indVariableCount);
            calcPastStats();
            calculateAccuracy();
        }
    }

    private void calculateAccuracy() {
        double sqVarSumFromMean = 0.0d;
        for (int i = 0; i < playerStats.size(); i++) {
            sqVarSumFromMean += (playerStats.get(i).getPoint() - pointMean) * (playerStats.get(i).getPoint() - pointMean);
        }
        double sqVarSumFromLine = 0.0d;
        for (int i = 0; i < playerStats.size(); i++) {
            sqVarSumFromLine += (predictPastPoint(i) - playerStats.get(i).getPoint()) * (predictPastPoint(i) - playerStats.get(i).getPoint());
        }
        accuracy = sqVarSumFromMean - sqVarSumFromLine;
        accuracy /= sqVarSumFromMean;
    }

    private void calculatePointMean() {
        if (playerStats.size() != 0) {
            pointMean = 0.0d;
            for (PlayerStats playerStat : playerStats) {
                pointMean += playerStat.getPoint();
            }
            pointMean = pointMean / ((double) playerStats.size());
        }
    }

    private void calcPastStats() {
        for (int i = 0; i < playerStats.size(); i++) {
            playerStats.get(i).predictedPoint = "" + predictPastPoint(i);
        }
    }

    public double predictFuturePoint() {
        double res = parameters[0];
        if (Config.indVariableCount >= 1)
            res += parameters[1] * getSel();
        if (Config.indVariableCount >= 2)
            res += parameters[2] * getMatchDayNo();
        if (Config.indVariableCount >= 3)
            res += parameters[3] * getCredit();
        if (playerStats.size() <= Config.indVariableCount) {
            res = (res + pointMean) / 2.0;
        }
        return res;
    }

    public double predictPastPoint(int index) {
        double res = parameters[0];
        if (Config.indVariableCount >= 1)
            res += parameters[1] * playerStats.get(index).getSel();
        if (Config.indVariableCount >= 2)
            res += parameters[2] * DateUtil.getDiffBetween(playerStats.get(index).date, playerStats.get(playerStats.size() - 1).date);
        if (Config.indVariableCount >= 3)
            res += parameters[3] * playerStats.get(index).getCredit();
        return res;
    }

    public String getPlayerName() {
        return playerName;
    }


    public void setPlayerAnnounced(String s) {
        playerAnnounced = s;
    }

}
