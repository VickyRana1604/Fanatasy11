package prediction;

import Config.Config;
import Data.FantasyData;
import Data.Player;
import Util.FileUtil;

import java.io.IOException;
import java.util.*;

public class FantasyTeams {

    private FantasyData fantasyData;
    //    private PriorityQueue<FantasyTeam> temp = new PriorityQueue<FantasyTeam>(new TeamComparator1());
//    private static final int Max = 10;
    private PriorityQueue<FantasyTeam> pQueue = new PriorityQueue<>(new TeamComparator());

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

            // return basedOnResultDiff(t1, t2);
            return basedOnTotalFuturePredictedPt(t1, t2);
//            return basedOnResult(t1, t2);

            //return basedOnDr11WithExpectedCVcResultDiff(t1,t2);
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
    }

    public FantasyTeams(FantasyData fantasyData) {
        this.fantasyData = fantasyData;
    }

    static int v = 0;

    private List<Player> getPlayerListFromStack(String s, List<Player> player) {
        List<Player> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i += 2) {
            result.add(player.get(Integer.parseInt("" + s.charAt(i) + s.charAt(i + 1))));
        }
        return result;
    }

    int f = 0;

    private void recurBowl(TeamArrangement ta, FantasyTeam team, int index, String s) {
        if (s.length() == ta.getBo()) {
            team.bowlPlayers = getPlayerListFromStack(s,fantasyData.bowlPlayer);
//            team.printTeam(v++);
            FantasyTeam tempTeam = new FantasyTeam(team);
            if (tempTeam.isFeasible()) {
                //+ " " + team.calCr());
                System.out.println("" + f++);
                //Analysis.getInstance().insertAccuracy(tempTeam.getTotAccuracy(), tempTeam.getTotActualPt(), tempTeam);
                pQueue.add(tempTeam);
            } else {

            }
            return;
        }

        for (int i = index; i < fantasyData.bowlPlayer.size(); i++) {
            recurBowl(ta, team, i + 1, getPlayerChar(i) + s);
        }
    }

    private void recurAllRounder(TeamArrangement ta, FantasyTeam team, int index, String s) {
        if (s.length() == ta.getA()) {
            team.allRounderPlayers = getPlayerListFromStack(s,fantasyData.allPlayer);
            recurBowl(ta, team, 0, "");
            return;
        }

        for (int i = index; i < fantasyData.allPlayer.size(); i++) {
            recurAllRounder(ta, team, i + 1, getPlayerChar(i) + s);
        }
    }


    private void recurBat(TeamArrangement ta, FantasyTeam team, int index, String s) {
        if (s.length() == ta.getBa()) {
            team.batPlayers = getPlayerListFromStack(s,fantasyData.batPlayer);
            recurAllRounder(ta, team, 0, "");
            return;
        }

        for (int i = index; i < fantasyData.batPlayer.size(); i++) {
            recurBat(ta, team, i + 1, getPlayerChar(i) + s);
        }
    }


    public void recurWicket(TeamArrangement ta, FantasyTeam team, int index, String s) {
        if (s.length() == ta.getW()) {
            team.wicketPlayers = getPlayerListFromStack(s,fantasyData.wkPlayer);
            recurBat(ta, team, 0, "");
            return;
        }

        for (int i = index; i < fantasyData.wkPlayer.size(); i++) {
            recurWicket(ta, team, i + 1, getPlayerChar(i) + s);
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
                recurWicket(arrangement, new FantasyTeam(), 0,"");
        else
            recurWicket(ta, new FantasyTeam(), 0, "");
//            for (int i = 0; i < Max; i++) {
//             Team t=pQueue.poll();
//                pls.add(new Team(t));
//            }
//            pQueue.clear();
//            for (Team t : pls)
//                pQueue.add(new Team(t));

    }

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
        int ct = 0;
        while (pQueue.size() != 0) {
            if (Double.compare(pQueue.peek().getTotalPredictedPt(), pt) >= 0) {
                FileUtil.getInstance().setStr(pQueue.peek().printTeam(Config.TEAM_COUNT++));
            }
            pQueue.poll();
        }
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
