package prediction;

import Config.Config;
import Data.FantasyData;
import Data.Player;
import Util.FileUtil;

import java.io.IOException;
import java.util.*;

public class FantasyTeams {

    private FantasyData fantasyData;
    private final String team1Name;

    //    private PriorityQueue<FantasyTeam> temp = new PriorityQueue<FantasyTeam>(new TeamComparator1());
//    private static final int Max = 10;
    FixSizePQueue pQueue = new FixSizePQueue(new TeamComparator());

    private class FixSizePQueue {
        private final static int maxTeamAllowed = 1000;
        private final TeamComparator comparator;
        private final PriorityQueue<FantasyTeam> minQueue;
        private final PriorityQueue<FantasyTeam> maxQueue;

        class TeamComparatorI implements Comparator<FantasyTeam> {
            private final TeamComparator comparator;

            public TeamComparatorI(TeamComparator comparator) {
                this.comparator = comparator;
            }

            @Override
            public int compare(FantasyTeam t1, FantasyTeam t2) {
                return -comparator.compare(t1, t2);
            }
        }

        public FixSizePQueue(TeamComparator comparator) {
            this.comparator = comparator;
            minQueue = new PriorityQueue<>(comparator);
            maxQueue = new PriorityQueue<>(new TeamComparatorI(comparator));
        }

        public void add(FantasyTeam team) {
            minQueue.add(team);
            maxQueue.add(team);

            if (minQueue.size() > maxTeamAllowed) {
                maxQueue.remove(minQueue.poll());
            }
        }

        public int size() {
            return maxQueue.size();
        }

        public FantasyTeam poll() {
            FantasyTeam team = maxQueue.poll();
            minQueue.remove(team);
            return  team;
        }
    }

    //private List<FantasyTeam> pQueue = new ArrayList<>();

//    private int basedOnResultDiff(FantasyTeam t1, FantasyTeam t2) {
//        double d1 = Math.abs(t1.getTotalPredictedPt() - t1.getTotActualPt());
//        double d2 = Math.abs(t2.getTotalPredictedPt() - t2.getTotActualPt());
//        if (d2 < d1)
//            return -1;
//        else if (d2 > d1)
//            return 1;
//        return 0;
//    }

//    private int basedOnDr11WithExpectedCVcResultDiff(Team t1, Team t2) {
//        double d1 = Math.abs(t1.getDR11ActualPt() - t1.getTotActualPt());
//        double d2 = Math.abs(t2.getDR11ActualPt() - t2.getTotActualPt());
//        if (d2 < d1)
//            return -1;
//        else if (d2 > d1)
//            return 1;
//        return 0;
//    }

//    private int basedOnActualResult(FantasyTeam t1, FantasyTeam t2) {
//        if (t2.getTotActualPt() < t1.getTotActualPt())
//            return -1;
//        else if (t2.getTotActualPt() > t1.getTotActualPt())
//            return 1;
//        return 0;
//    }

    private int basedOnTotalFuturePredictedPt(FantasyTeam t1, FantasyTeam t2) {
        if (t2.getTotalPredictedPt() < t1.getTotalPredictedPt())
            return -1;
        else if (t2.getTotalPredictedPt() > t1.getTotalPredictedPt())
            return 1;
        return 0;
    }

//    private int basedOnTotAccuracy(FantasyTeam t1, FantasyTeam t2) {
//        return -Double.compare(t1.getTotAccuracy(), t2.getTotAccuracy());
//    }

    class TeamComparator implements Comparator<FantasyTeam> {
        @Override
        public int compare(FantasyTeam t1, FantasyTeam t2) {
            return -basedOnTotalFuturePredictedPt(t1, t2);
        }
    }

//
//    class TeamComparator1 implements Comparator<FantasyTeam> {
//        @Override
//        public int compare(FantasyTeam t1, FantasyTeam t2) {
//            //return Double.compare(t1.getTotAccuracy(),t2.getTotAccuracy());
//            return basedOnActualResult(t1, t2);
//        }
//    }

    public FantasyTeams(TeamArrangement ta) {
        findAllTeams(ta);
        team1Name = "";
    }

    public FantasyTeams(FantasyData fantasyData) {
        this.fantasyData = fantasyData;
        team1Name = fantasyData.wkPlayer.get(0).playerTeamName;
    }

    static int v = 0;


    int f = 0;
    private long startTime = System.currentTimeMillis();

//        private void recurBowl(TeamArrangement ta, FantasyTeam team, int index, String s, double totCr) {
//        if (s.length() == ta.getBo()) {
//
//            long curr = (System.currentTimeMillis() - startTime) / 1000;
//            team.strbowlPlayers = s;
//            f++;
////            team.printTeam(v++);
////
//            if (team.isFeasible()) {
////                f++;
////                //+ " " + team.calCr());
////                //System.out.println("" + f++);
////                //Analysis.getInstance().insertAccuracy(tempTeam.getTotAccuracy(), tempTeam.getTotActualPt(), tempTeam);;
////                long startTime = System.currentTimeMillis();
//                 pQueue.add(new FantasyTeam(team, fantasyData));
////                // System.out.println(System.currentTimeMillis()-startTime);
//            }
//            return;
//        }
//
//        for (int i = index; i < fantasyData.bowlPlayer.size(); i++) {
//            if ((totCr + fantasyData.bowlPlayer.get(i).getCredit()) <= 100.0)
//                recurBowl(ta, team, i + 1, getPlayerChar(i) + s, totCr + fantasyData.bowlPlayer.get(i).getCredit());
//
//        }
//    }
//
//    private void recurAllRounder(TeamArrangement ta, FantasyTeam team, int index, String s, double totCr) {
//        if (s.length() == ta.getA()) {
//            team.strallRounderPlayers = s;
//            recurBowl(ta, team, 0, "", totCr);
//            return;
//        }
//
//        for (int i = index; i < fantasyData.allPlayer.size(); i++) {
//            if ((totCr + fantasyData.allPlayer.get(i).getCredit()) <= 100.0)
//                recurAllRounder(ta, team, i + 1, getPlayerChar(i) + s, totCr + fantasyData.allPlayer.get(i).getCredit());
//        }
//    }
//
//
//    private void recurBat(TeamArrangement ta, FantasyTeam team, int index, String s, double totCr) {
//        if (s.length() == ta.getBa()) {
//            team.strbatPlayers = s;
//            recurAllRounder(ta, team, 0, "", totCr);
//            return;
//        }
//
//        for (int i = index; i < fantasyData.batPlayer.size(); i++) {
//            if ((totCr + fantasyData.batPlayer.get(i).getCredit()) <= 100.0)
//                recurBat(ta, team, i + 1, getPlayerChar(i) + s, totCr + fantasyData.batPlayer.get(i).getCredit());
//        }
//    }
//    private void recurWicket(TeamArrangement ta, FantasyTeam team, int index, String s, double totCr) {
//        if (s.length() == ta.getW()) {
//            team.strwicketPlayers = s;
//            recurBat(ta, team, 0, "", totCr);
//            return;
//        }
//
//        for (int i = index; i < fantasyData.wkPlayer.size(); i++) {
//            if ((totCr + fantasyData.wkPlayer.get(i).getCredit()) <= 100.0)
//                recurWicket(ta, team, i + 1, getPlayerChar(i) + s, totCr + fantasyData.wkPlayer.get(i).getCredit());
//        }
//    }
    public void recurBowl(TeamArrangement ta, FantasyTeam team, int index, String s, double totCredit, int t1Strength, int t2Strength, double futureTeamPt) {
        if (s.length() == ta.getBo()) {

            long curr = (System.currentTimeMillis() - startTime) / 1000;
            team.strbowlPlayers = s;
            team.totCr = totCredit;
            team.totPredictedFuturePt = futureTeamPt;
            f++;
//            team.printTeam(v++);
//
//            if (team.isFeasible()) {
//                f++;
//                //+ " " + team.calCr());
//                //System.out.println("" + f++);
//                //Analysis.getInstance().insertAccuracy(tempTeam.getTotAccuracy(), tempTeam.getTotActualPt(), tempTeam);;
//                long startTime = System.currentTimeMillis();
            pQueue.add(new FantasyTeam(team, fantasyData));
//                // System.out.println(System.currentTimeMillis()-startTime);
//            }
            return;
        }

        for (int i = index; i < fantasyData.bowlPlayer.size(); i++) {
            int t1 = fantasyData.bowlPlayer.get(i).playerTeamName.equals(team1Name) ? 1 : 0;
            int t2 = fantasyData.bowlPlayer.get(i).playerTeamName.equals(team1Name) ? 0 : 1;
            if ((totCredit + fantasyData.bowlPlayer.get(i).getCredit()) <= 100.0 && (t1 + t1Strength) <= 7 && (t2 + t2Strength) <= 7)
                recurBowl(ta, team, i + 1, getPlayerChar(i) + s,
                        totCredit + fantasyData.bowlPlayer.get(i).getCredit(),
                        t1 + t1Strength,
                        t2 + t2Strength,
                        futureTeamPt + fantasyData.bowlPlayer.get(i).predictFuturePoint());
        }
    }

    public void recurAllRounder(TeamArrangement ta, FantasyTeam team, int index, String s, double totCredit, int t1Strength, int t2Strength, double futureTeamPt) {
        if (s.length() == ta.getA()) {
            team.strallRounderPlayers = s;
            recurBowl(ta, team, 0, "", totCredit, t1Strength, t2Strength, futureTeamPt);
            return;
        }

        for (int i = index; i < fantasyData.allPlayer.size(); i++) {
            int t1 = fantasyData.allPlayer.get(i).playerTeamName.equals(team1Name) ? 1 : 0;
            int t2 = fantasyData.allPlayer.get(i).playerTeamName.equals(team1Name) ? 0 : 1;
            if ((totCredit + fantasyData.allPlayer.get(i).getCredit()) <= 100.0 && (t1 + t1Strength) <= 7 && (t2 + t2Strength) <= 7)
                recurAllRounder(ta, team, i + 1, getPlayerChar(i) + s,
                        totCredit + fantasyData.allPlayer.get(i).getCredit(),
                        t1 + t1Strength,
                        t2 + t2Strength,
                        futureTeamPt + fantasyData.allPlayer.get(i).predictFuturePoint());
        }
    }

    public void recurBat(TeamArrangement ta, FantasyTeam team, int index, String s, double totCredit, int t1Strength, int t2Strength, double futureTeamPt) {
        if (s.length() == ta.getBa()) {
            team.strbatPlayers = s;
            recurAllRounder(ta, team, 0, "", totCredit, t1Strength, t2Strength, futureTeamPt);
            return;
        }

        for (int i = index; i < fantasyData.batPlayer.size(); i++) {
            int t1 = fantasyData.batPlayer.get(i).playerTeamName.equals(team1Name) ? 1 : 0;
            int t2 = fantasyData.batPlayer.get(i).playerTeamName.equals(team1Name) ? 0 : 1;
            if ((totCredit + fantasyData.batPlayer.get(i).getCredit()) <= 100.0 && (t1 + t1Strength) <= 7 && (t2 + t2Strength) <= 7)
                recurBat(ta, team, i + 1, getPlayerChar(i) + s,
                        totCredit + fantasyData.batPlayer.get(i).getCredit(),
                        t1 + t1Strength,
                        t2 + t2Strength,
                        futureTeamPt + fantasyData.batPlayer.get(i).predictFuturePoint());
        }
    }

    public void recurWicket(TeamArrangement ta, FantasyTeam team, int index, String s, double totCredit, int t1Strength, int t2Strength, double futureTeamPt) {
        if (s.length() == ta.getW()) {
            team.strwicketPlayers = s;
            recurBat(ta, team, 0, "", totCredit, t1Strength, t2Strength, futureTeamPt);
            return;
        }

        for (int i = index; i < fantasyData.wkPlayer.size(); i++) {
            int t1 = fantasyData.wkPlayer.get(i).playerTeamName.equals(team1Name) ? 1 : 0;
            int t2 = fantasyData.wkPlayer.get(i).playerTeamName.equals(team1Name) ? 0 : 1;
            if ((totCredit + fantasyData.wkPlayer.get(i).getCredit()) <= 100.0 && (t1 + t1Strength) <= 7 && (t2 + t2Strength) <= 7)
                recurWicket(ta, team, i + 1, getPlayerChar(i) + s,
                        totCredit + fantasyData.wkPlayer.get(i).getCredit(),
                        t1 + t1Strength,
                        t2 + t2Strength,
                        futureTeamPt + fantasyData.wkPlayer.get(i).predictFuturePoint());
        }
    }

    private String getPlayerChar(int index) {
        String res = "" + index;
        if (res.length() == 1) return "0" + res;
        return res;
    }

    public void findAllTeams(TeamArrangement ta) {

        List<FantasyTeam> pls = new ArrayList<>();
        if (ta == null)
            for (TeamArrangement arrangement : Config.getInstance().teamArrangements)
                recurWicket(arrangement, new FantasyTeam(fantasyData), 0, "", 0.0,0,0,0.0);
//        else
//            recurWicket(ta, new FantasyTeam(fantasyData), 0, "", 0.0, 0, 0, 0.0);
//            for (int i = 0; i < Max; i++) {
//             Team t=pQueue.poll();
//                pls.add(new Team(t));
//            }
//            pQueue.clear();
//            for (Team t : pls)
//                pQueue.add(new Team(t));
        System.out.println(f);
    }

    private List<Player> getPlayerListFromStack(List<Player> player, Stack<Integer> stack) {
        List<Player> result = new ArrayList<>();
        for (Integer i : stack) {
            result.add(player.get(i));
        }
        return result;
    }

//    int getPlayerIndex(String pl) {
//        return Integer.parseInt("" + pl.charAt(pl.length() - 2) + pl.charAt(pl.length() - 1));
//    }
//
//    private void pushPlayer(List<Player> players, Stack<Integer> plStack, int maxPl) {
//        for (int i = plStack.peek() + 1; i < players.size() && plStack.size() <= maxPl; i++) {
//            plStack.push(i);
//        }
//    }
//
//    private void doWork(List<Player> players, Stack<Integer> plStack, int maxPl) {
//
//    }
//
//    public void loopAllPossibleWKPlayers(TeamArrangement ta, FantasyTeam team) {
//        String playerList = "01";
//        Stack<Integer> plStack = new Stack<>();
//        plStack.push(0);
//        while (plStack.size() != 0) {
//            pushPlayer(fantasyData.wkPlayer, plStack, ta.getW());
//            if (plStack.size() == ta.getW()) {
//                getPlayerListFromStack(fantasyData.wkPlayer,plStack));
//            }
//            popPlayer();
//        }
//    }

    private void showAllTeams(int ct) throws IOException {

        while (pQueue.size() != 0) {
            FileUtil.getInstance().printTeams(pQueue.poll().printTeam(Config.TEAM_COUNT++));
        }
    }

//    private void showTopTeams(int T) throws IOException {
//        int ct = 0;
//
//
//        topSafeTeam(FileUtil.getInstance().writer1);
//        topSafeTeam(FileUtil.getInstance().writer2);
//        topSafeTeam(FileUtil.getInstance().writer3);
//        topSafeTeam(FileUtil.getInstance().writer4);
//        topSafeTeam(FileUtil.getInstance().writer5);
//
//        printRem(FileUtil.getInstance().writer1);
//        printRem(FileUtil.getInstance().writer2);
//        printRem(FileUtil.getInstance().writer3);
//        printRem(FileUtil.getInstance().writer4);
//        printRem(FileUtil.getInstance().writer5);
//
//    }
//
//    private void topSafeTeam(BufferedWriter writer) throws IOException {
//        String f = "\nChange C VC and replace 1 or 2 bowlers or baters\n";
//        int t = 2;
//        writer.write(f);
//        while (t > 0 && pQueue.size() != 0) {
//            t--;
//            writer.write(pQueue.poll().printTeam(2 - t));
//        }
//    }
//
//    private void printRem(BufferedWriter writer) throws IOException {
//        List<FantasyTeam> teamList = new ArrayList<>();
//
//
//        String f1 = "\nDont change C VC and plauers\n";
//
//        writer.write(f1);
//        Random random1 = new Random();
//        int t = 10000;
//        while ((t--) > 0 && pQueue.size() != 0)
//            teamList.add(pQueue.poll());
//
//        t = 18;
//        while ((t--) > 0) {
//            int index = random1.nextInt(teamList.size());
//            writer.write(teamList.get(index).printTeam(20 - t));
//            teamList.remove(index);
//        }
//    }
//
//    private void printRandomise(BufferedWriter writer) throws IOException {
//        List<FantasyTeam> teamList = new ArrayList<>();
//
//        Random random1 = new Random();
//        while (pQueue.size() != 0)
//            teamList.add(pQueue.poll());
//
//
////        while () {
////            int index = random1.nextInt(teamList.size());
////            writer.write(teamList.get(index).printTeam(20 - t));
////            teamList.remove(index);
////        }
//    }

    private void showDreamTeam(double pt) throws IOException {
//        int ct = 0;
//        while (pQueue.size() != 0) {
//            if (Double.compare(pQueue.peek().getTotalPredictedPt(), pt) >= 0) {
//                FileUtil.getInstance().setStr(pQueue.peek().printTeam(Config.TEAM_COUNT++));
//            }
//            pQueue.poll();
//        }
    }

//    private void showNUmbering() throws IOException {
//        int ct = 0;
//        while (pQueue.size() != 0) {
//
//            FantasyTeam team = pQueue.poll();
//            team.number = ct++;
//            temp.add(team);
//        }
//
//        ct = 0;
//        int T = 22;
//        while (temp.size() != 0) {
//            T--;
//            FileUtil.getInstance().writer1.write(temp.poll().printTeam(ct++));
//        }
//    }

    public void showTeams(int count) throws IOException {

        //showDreamTeam(696.0);
        //List<Team> a= (List<Team>) Arrays.asList(hash_Set);
        //showNUmbering();
        //showTopTeams(count);
        showAllTeams(count);
        //showDreamTeam(600.0);
        //Analysis.getInstance().printProb();
    }

}
