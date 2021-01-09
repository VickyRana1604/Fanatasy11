package prediction;

import Data.FantasyData;
import Data.Player;

import java.util.*;

class FantasyTeam {
    public int number;
    public String strwicketPlayers;
    public String strbatPlayers;
    public String strallRounderPlayers;
    public String strbowlPlayers;
    public double totCr;
    public double totPredictedFuturePt = -1.0d;
    private int team1Strength;
    //List<Player> players = new ArrayList<>();
    private PriorityQueue<Player> players = new PriorityQueue<>(new PlayerComparator1());
    private int thirtyFivePointMark;
    public Player first;
    public Player second;
    private Random random = new Random();
    private final FantasyData fantasyData;

    class PlayerComparator1 implements Comparator<Player> {

        @Override
        public int compare(Player p1, Player p2) {

            if (p2.predictFuturePoint() < p1.predictFuturePoint())
                return -1;
            else if (p2.predictFuturePoint() > p1.predictFuturePoint())
                return 1;
            return 0;
        }
    }

//    private PriorityQueue<Player> q2 = new PriorityQueue<>(new PlayerComparator2());
//
//    class PlayerComparator2 implements Comparator<Player> {
//
//        @Override
//        public int compare(Player p1, Player p2) {
//
//            if (p2.getResult() < p1.getResult())
//                return -1;
//            else if (p2.getResult() > p1.getResult())
//                return 1;
//            return 0;
//        }
//    }

    public FantasyTeam(FantasyData fantasyData) {
        this.fantasyData = fantasyData;
    }

    private List<Player> getPlayerList(String s, List<Player> player) {
        List<Player> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i += 2) {
            result.add(player.get(Integer.parseInt("" + s.charAt(i) + s.charAt(i + 1))));
        }
        return result;
    }

    public FantasyTeam(FantasyTeam o, FantasyData fantasyData) {
        this.fantasyData = fantasyData;

        //   long startTime = System.currentTimeMillis();
        strbowlPlayers = o.strbowlPlayers;
        strallRounderPlayers = o.strallRounderPlayers;
        strbatPlayers = o.strbatPlayers;
        strwicketPlayers = o.strwicketPlayers;
        totCr=o.totCr;
        totPredictedFuturePt=o.totPredictedFuturePt;

//        players.addAll(getPlayerListFromStack(o.strwicketPlayers, fantasyData.wkPlayer));
//        players.addAll(getPlayerListFromStack(o.strbatPlayers, fantasyData.batPlayer));
//        players.addAll(getPlayerListFromStack(o.strallRounderPlayers, fantasyData.allPlayer));
//        players.addAll(getPlayerListFromStack(o.strbowlPlayers, fantasyData.bowlPlayer));

//        thirtyFivePointMark = 0;
//        while (q1.size() != 0) {
//            players.add(q1.poll());
////            if(Double.compare(players.get(players.size()-1).avgPt,35.0)==1){
////                thirtyFivePointMark++;
////            }
//        }
//        selectCandVC();
        // System.out.println("time taken" + (System.currentTimeMillis() - startTime));
    }

    private void selectCandVC() {

        players.addAll(getPlayerList(strwicketPlayers, fantasyData.wkPlayer));
        players.addAll(getPlayerList(strbatPlayers, fantasyData.batPlayer));
        players.addAll(getPlayerList(strallRounderPlayers, fantasyData.allPlayer));
        players.addAll(getPlayerList(strbowlPlayers, fantasyData.bowlPlayer));
        first = players.poll();

        second = players.poll();
        players.add(first);
        players.add(second);
        //
// if (thirtyFivePointMark == 11) ;
//        thirtyFivePointMark = 7;
//        int bound = 1000000;
//        int section = random.nextInt(bound);
//        if (section > (4 * (bound / 10))) {
//            first = players.get(random.nextInt(thirtyFivePointMark));
//        } else {
//            first = players.get(random.nextInt(11 - thirtyFivePointMark) + thirtyFivePointMark);
//        }
//
//        while (second == null || second.playerName.equals(first.playerName)) {
//            section = random.nextInt(bound);
//            if (section > (4 * (bound / 10))) {
//                second = players.get(random.nextInt(thirtyFivePointMark));
//            } else {
//                second = players.get(random.nextInt(11 - thirtyFivePointMark) + thirtyFivePointMark);
//            }
//        }
    }

    private boolean isLESeven() {
        return (team1Strength <= 7) && (team1Strength >= 4);
    }

    private boolean isLessThanHndrd() {
        return Double.compare(totCr, 100.0) <= 0;
    }

    private void calBasicParameters(){
        totCr =totPredictedFuturePt= 0.0;
        team1Strength = 0;
        String team1Name = fantasyData.wkPlayer.get(Integer.parseInt("" + strwicketPlayers.charAt(0) + strwicketPlayers.charAt(1))).playerTeamName;
        for (int i = 0; i < strwicketPlayers.length(); i += 2) {
            totCr += fantasyData.wkPlayer
                    .get(Integer.parseInt("" + strwicketPlayers.charAt(i) + strwicketPlayers.charAt(i + 1)))
                    .getCredit();

            totPredictedFuturePt += fantasyData.wkPlayer
                    .get(Integer.parseInt("" + strwicketPlayers.charAt(i) + strwicketPlayers.charAt(i + 1)))
                    .predictFuturePoint();

            if (fantasyData.wkPlayer
                    .get(Integer.parseInt("" + strwicketPlayers.charAt(i) + strwicketPlayers.charAt(i + 1)))
                    .playerTeamName.equals(team1Name)) team1Strength++;
        }
        for (int i = 0; i < strbatPlayers.length(); i += 2) {
            totCr += fantasyData.batPlayer
                    .get(Integer.parseInt("" + strbatPlayers.charAt(i) + strbatPlayers.charAt(i + 1)))
                    .getCredit();

            totPredictedFuturePt += fantasyData.batPlayer
                    .get(Integer.parseInt("" + strbatPlayers.charAt(i) + strbatPlayers.charAt(i + 1)))
                    .predictFuturePoint();

            if (fantasyData.batPlayer
                    .get(Integer.parseInt("" + strbatPlayers.charAt(i) + strbatPlayers.charAt(i + 1)))
                    .playerTeamName.equals(team1Name)) team1Strength++;
        }
        for (int i = 0; i < strallRounderPlayers.length(); i += 2) {
            totCr += fantasyData.allPlayer
                    .get(Integer.parseInt("" + strallRounderPlayers.charAt(i) + strallRounderPlayers.charAt(i + 1)))
                    .getCredit();

            totPredictedFuturePt += fantasyData.allPlayer
                    .get(Integer.parseInt("" + strallRounderPlayers.charAt(i) + strallRounderPlayers.charAt(i + 1)))
                    .predictFuturePoint();

            if (fantasyData.allPlayer
                    .get(Integer.parseInt("" + strallRounderPlayers.charAt(i) + strallRounderPlayers.charAt(i + 1)))
                    .playerTeamName.equals(team1Name)) team1Strength++;
        }
        for (int i = 0; i < strbowlPlayers.length(); i += 2) {
            totCr += fantasyData.bowlPlayer
                    .get(Integer.parseInt("" + strbowlPlayers.charAt(i) + strbowlPlayers.charAt(i + 1)))
                    .getCredit();
            totPredictedFuturePt += fantasyData.bowlPlayer
                    .get(Integer.parseInt("" + strbowlPlayers.charAt(i) + strbowlPlayers.charAt(i + 1)))
                    .predictFuturePoint();
            if (fantasyData.bowlPlayer
                    .get(Integer.parseInt("" + strbowlPlayers.charAt(i) + strbowlPlayers.charAt(i + 1)))
                    .playerTeamName.equals(team1Name)) team1Strength++;
        }
    }
    public boolean isFeasible() {
        calBasicParameters();
        return isLessThanHndrd() && isLESeven();
    }

    public double getTotalPredictedPt() {
        return totPredictedFuturePt;
        // first.getAvgPt() + (second.getAvgPt()) / 2;
//
//        for (Player p : players) {
//            totPredictedFuturePt += p.predictFuturePoint();
//        }
//        return totPredictedFuturePt;
    }

//    public double getTotActualPt() {
//        char c;
//
//        double totActPt = 0;// first.getResult() + (second.getResult()) / 2;
//
//        for (Player p : players) {
//            totActPt += p.getResult();
//        }
//        return totActPt;
//    }

    public double getTeamTotalAccuracy() {

        double teamTotalAccuracy = 0;// first.getResult() + (second.getResult()) / 2;

        for (Player p : players) {
            teamTotalAccuracy += p.accuracy;
        }
        teamTotalAccuracy *= 100.0;
        teamTotalAccuracy /= 11;
        return teamTotalAccuracy;
    }

    int k;

    private String getCorVC(Player p) {
        if (k == 2) return "";
        if (p.playerName.equals(first.playerName)) {
            k++;
            return "(C)";
        }
        if (p.playerName.equals(second.playerName)) {
            k++;
            return "(VC)";
        }
        return "";
    }

    public String printTeam(int teamNo) {
        selectCandVC();
        k = 0;
        String str = "\n=====================TEAM NO: " + teamNo + " | Cr: " + totCr + " | ExpectedPoint: " + getTotalPredictedPt() + " | " + getTeamTotalAccuracy() + "% | " + number + "================";
        str += "\n" + strwicketPlayers + " " + strbatPlayers + " " + strallRounderPlayers + " " + strbowlPlayers;
        char c;
        String temp = "";

        for (Player player : getPlayerList(strwicketPlayers, fantasyData.wkPlayer)) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }

        str += "\nWICKET\n" + temp;
        temp = "";

        for (Player player : getPlayerList(strbatPlayers, fantasyData.batPlayer)) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }

        str += "\nBAT\n" + temp;
        temp = "";

        for (Player player : getPlayerList(strallRounderPlayers, fantasyData.allPlayer)) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }

        str += "\nALL ROUNDER\n" + temp;
        temp = "";

        for (Player player : getPlayerList(strbowlPlayers, fantasyData.bowlPlayer)) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }
        str += "\nBOWL\n" + temp;
        str += "\n==========================TEAM ENDS===================\n";
        return str;
    }

    //
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + Integer.parseInt(wicketStr);
//            result = prime * result + Integer.parseInt(batStr);
//            result = prime * result + Integer.parseInt(allRounderStr);
//            result = prime * result + Integer.parseInt(bowlStr);
//            return result;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj)
//                return true;
//            if (obj == null)
//                return false;
//            if (getClass() != obj.getClass())
//                return false;
//            Team other = (Team) obj;
//            return !(!wicketStr.equalsIgnoreCase(other.wicketStr)
//                    || !allRounderStr.equalsIgnoreCase(other.allRounderStr)
//                    || !batStr.equalsIgnoreCase(other.batStr)
//                    || !bowlStr.equalsIgnoreCase(other.bowlStr)
//                    || wicketMax != other.wicketMax
//                    || batMax != other.batMax
//                    || allRounderMax != other.allRounderMax
//                    || bowlMax != other.bowlMax);
//
//        }
}
