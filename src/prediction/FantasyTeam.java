package prediction;

import Data.Player;

import java.util.*;

class FantasyTeam {
    public int number;
    public List<Player> wicketPlayers;
    public List<Player> batPlayers;
    public List<Player> allRounderPlayers;
    public List<Player> bowlPlayers;
    List<Player> players = new ArrayList<>();
    private PriorityQueue<Player> q1 = new PriorityQueue<>(new PlayerComparator1());
    private int thirtyFivePointMark;
    public Player first;
    public Player second;
    private Random random = new Random();

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

    public FantasyTeam() {
        wicketPlayers = new ArrayList<>();
        batPlayers = new ArrayList<>();
        allRounderPlayers = new ArrayList<>();
        bowlPlayers = new ArrayList<>();
    }

    public FantasyTeam(FantasyTeam o) {

        wicketPlayers = o.wicketPlayers;
        batPlayers = o.batPlayers;
        allRounderPlayers = o.allRounderPlayers;
        bowlPlayers = o.bowlPlayers;

        for (Player player : wicketPlayers) {
            q1.add(player);
        }

        for (Player player : batPlayers) {
            q1.add(player);
        }

        for (Player player : allRounderPlayers) {
            q1.add(player);
        }

        for (Player player : bowlPlayers) {
            q1.add(player);
        }


        thirtyFivePointMark = 0;
        while (q1.size() != 0) {
            players.add(q1.poll());
//            if(Double.compare(players.get(players.size()-1).avgPt,35.0)==1){
//                thirtyFivePointMark++;
//            }
        }
        selectCandVC();
    }

    private void selectCandVC() {

        first = players.get(0);
        second = players.get(1);
//        //if (thirtyFivePointMark == 11) ;
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
        int team1Strength = 0;
        String team1Name = players.get(0).playerTeamName;

        for (Player p : players) {
            if (p.playerTeamName.equals(team1Name)) team1Strength++;
        }

        if (team1Strength > 7 || team1Strength < 4) {
            return false;
        }
        return true;
    }

    private double calTotalCredit() {
        double totCr = 0.0;
        for (Player p : players)
            totCr += p.getCredit();
        return totCr;
    }

    private boolean isLessThanHndrd() {
        return Double.compare(calTotalCredit(), 100.0) <= 0;
    }

    public boolean isFeasible() {
        return isLessThanHndrd() && isLESeven();
    }

    public double getTotalPredictedPt() {

        double totPredictedFuturePt = 0.0d;// first.getAvgPt() + (second.getAvgPt()) / 2;

        for (Player p : players) {
            totPredictedFuturePt += p.predictFuturePoint();
        }
        return totPredictedFuturePt;
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
        k = 0;
        String str = "\n=====================TEAM NO: " + teamNo + " | Cr: " + calTotalCredit() + " | ExpectedPoint: " + getTotalPredictedPt() + " | " + getTeamTotalAccuracy() + "% | " + number + "================";
        str += "\n" + wicketPlayers + " " + batPlayers + " " + allRounderPlayers + " " + bowlPlayers;
        char c;
        String temp = "";

        for (Player player : wicketPlayers) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }

        str += "\nWICKET\n" + temp;
        temp = "";

        for (Player player : batPlayers) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }

        str += "\nBAT\n" + temp;
        temp = "";

        for (Player player : allRounderPlayers) {
            temp += getCorVC(player) + player.playerName + "(" + player.predictFuturePoint() + ") | ";
        }

        str += "\nALL ROUNDER\n" + temp;
        temp = "";

        for (Player player : bowlPlayers) {
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
