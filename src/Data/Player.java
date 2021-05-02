package Data;

import Config.Config;
import Util.DateUtil;
import Util.RegressionUtil;

import java.util.*;

public class Player {
    public double result;
    public String playerName;
    public String playerTeamName;
    public String playerAnnounced;
    public String playerCredit;
    public String playerSel;
    public double[] parameters;
    public double accuracy;
    public double pointMean;
    public double futurePt;
    public List<PlayerStats> playerStats;
    public boolean playedLastMatch;


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
        futurePt = -10000000.0d;
        this.playedLastMatch = playerStats.size() > 0 && !playerStats.get(0).point.equals("DNP");
        calculatePointMean();
        estimateParameters();
        int a = 1;
    }

    public List<PlayerStats> getNoNDNPData() {
        List<PlayerStats> result = new ArrayList<>();
        for (PlayerStats stats : playerStats) {
            if (!stats.point.equals("DNP")) {
                result.add(stats);
            }
        }
        return result;
    }

    private double[][] getXStats() {
        List<PlayerStats> playerStats = getNoNDNPData();
        double[][] res = new double[Config.indVariableCount][playerStats.size()];

        int indVarCount = 0;

        if (Config.indVariableCount >= 1) {
            //variable date
            for (int i = 0; i < playerStats.size(); i++) {
                res[indVarCount][i] = playerStats.size()-i;//getMatchDayNo(DateUtil.parseDate(playerStats.get(i).date));
            }
            indVarCount++;
        }

        //variable sel
        if (Config.indVariableCount >= 2) {
            for (int i = 0; i < playerStats.size(); i++) {
                res[indVarCount][i] = playerStats.get(i).getSel() + (new Random()).nextInt(100000) / 100000000.0;
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
        List<PlayerStats> playerStats = getNoNDNPData();
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
        //if (Double.compare(plCredit, 0.0d) == 0)
        return Double.parseDouble(playerCredit);
        //  return plCredit;
    }

    public double getMatchDayNo(Date time) {
        try {
            return DateUtil.getDiffBetween(time, DateUtil.parseDate(playerStats.get(playerStats.size() - 1).date));
        } catch (IndexOutOfBoundsException e) {
            return 1;
        }
    }

    public void estimateParameters() {
        try {
            if (getNoNDNPData().size() >= (Config.indVariableCount + 1)) {
                //parameters = RegressionUtil.twoVarLRegress(getYStats());
                parameters = RegressionUtil.estimateParameters(getXStats(), getYStats(), Config.indVariableCount);
                calcPastStats();
                calculateAccuracy();
            }
        } catch (Exception e) {
            int a = 1;
        }
    }

    private void calculateAccuracy() {
        //accuracy = RegressionUtil.getAccuracy(getYStats(), parameters);

        List<PlayerStats> playerStats = getNoNDNPData();
        double sqVarSumFromMean = 0.0d;
        for (int i = 0; i < playerStats.size(); i++) {
            sqVarSumFromMean += (playerStats.get(i).getPoint() - pointMean) * (playerStats.get(i).getPoint() - pointMean);
        }
        double sqVarSumFromLine = 0.0d;
        for (int i = 0; i < playerStats.size(); i++) {
            sqVarSumFromLine += (predictPastPoint(playerStats.size()-i,playerStats.get(i)) - playerStats.get(i).getPoint()) * (predictPastPoint(playerStats.size()-i,playerStats.get(i)) - playerStats.get(i).getPoint());
        }
        accuracy = sqVarSumFromMean - sqVarSumFromLine;
        accuracy = Double.compare(sqVarSumFromMean, 0.0) == 0 ? 1.0 : (accuracy / sqVarSumFromMean);
    }

    private void calculatePointMean() {
        List<PlayerStats> playerStats = getNoNDNPData();
        if (playerStats.size() != 0) {
            pointMean = 0.0d;
            for (PlayerStats playerStat : playerStats) {
                pointMean += playerStat.getPoint();
            }
            pointMean = pointMean / ((double) playerStats.size());
        }
    }

    private void calcPastStats() {
        List<PlayerStats> playerStats = getNoNDNPData();
        for (int i = 0; i < playerStats.size(); i++) {
            playerStats.get(i).predictedPoint = "" + predictPastPoint(playerStats.size()-i,playerStats.get(i));
        }
    }

    public double predictFuturePoint() {
        try {
            if (Double.compare(futurePt, -10000000.0d) == 0) {
                futurePt = parameters[0];
                if (Config.indVariableCount >= 1)
                    futurePt += parameters[1] * (this.playerStats.size()+1);//  getMatchDayNo(Calendar.getInstance().getTime());
                if (Config.indVariableCount >= 2)
                    futurePt += parameters[2] * getSel();
                if (Config.indVariableCount >= 3)
                    futurePt += parameters[3] * getCredit();
                if (playerStats.size() <= Config.indVariableCount) {
                    futurePt = (futurePt + pointMean) / 2.0;
                }
            }
        } catch (Exception e) {
            int a = 1;
        }
        return futurePt;
    }

    public double predictPastPoint(double dayNo,PlayerStats playerStats) {
        double res = parameters[0];
        if (Config.indVariableCount >= 1)
            res += parameters[1] * dayNo;//getMatchDayNo(DateUtil.parseDate(playerStats.date));
        if (Config.indVariableCount >= 2)
            res += parameters[2] * playerStats.getSel();
        if (Config.indVariableCount >= 3)
            res += parameters[3] * playerStats.getCredit();
        return res;
    }

    public String getPlayerName() {
        return playerName;
    }


    public void setPlayerAnnounced(String s) {
        playerAnnounced = s;
    }

}

