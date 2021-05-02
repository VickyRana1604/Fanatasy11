package prediction;


import Util.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Analysis {
    double[] percentCount;
    double[] maxPt;
    double[] minPt;
    FantasyTeam[] mnTeam;
    FantasyTeam[] mxTeam;
    double count;
    private static int range = 201;
    private static double deno = 100.0 / ((double) (range - 1));
    //    private PriorityQueue<FantasyTeam> imps = new PriorityQueue<FantasyTeam>(new TeamComparator());
//    int WINNING_ACCURACY = 75;
    double lB = (0.73 * range);
    double uB = (0.96 * range);

//
//    class TeamComparator implements Comparator<FantasyTeam> {
//
//
//        @Override
//        public int compare(FantasyTeam t1, FantasyTeam t2) {
//
//            // return basedOnResultDiff(t1, t2);
//            return -Double.compare(t1.getTotActualPt(), t2.getTotActualPt());
////            return basedOnResult(t1, t2);
//
//            //return basedOnDr11WithExpectedCVcResultDiff(t1,t2);
//        }
//    }

    private static Analysis analysis;

//    public void printImpTeams() throws IOException {
//        Dr11Data.TEAM_COUNT = 0;
//        while (imps.size() != 0) {
//            FileUtil.getInstance().writer1.write(imps.poll().printTeam(Dr11Data.TEAM_COUNT++));
//        }
//    }
//
//    private void storeImpTeam(FantasyTeam team) {
//        if (((int) team.getTeamTotalAccuracy()) == WINNING_ACCURACY)
//            imps.add(team);
//    }

    public static Analysis getInstance() {
        if (analysis == null) {
            analysis = new Analysis();
        }
        return analysis;
    }
//
//    private Analysis() {
//        percentCount = new double[range];
//        maxPt = new double[range];
//        minPt = new double[range];
//        mnTeam = new FantasyTeam[range];
//        mxTeam = new FantasyTeam[range];
//        for (int i = 0; i < range; i++) percentCount[i] = 0.0;
//
//        for (int i = 0; i < range; i++) maxPt[i] = -1.0;
//
//        for (int i = 0; i < range; i++) minPt[i] = 10000000.0;
//
//        for (int i = 0; i < range; i++) mnTeam[i] = mxTeam[i] = null;
//        count = 0;
//    }
//
//    public void insertAccuracy(double accuracy, double pt, FantasyTeam tm) {
//        storeImpTeam(tm);
//        int index = (int) (accuracy / deno);
//        percentCount[index] += 1.0;
//        count += 1.0;
//        if (Double.compare(minPt[index], pt) > 0) {
//            mnTeam[index] = tm;
//            minPt[index] = pt;
//        }
//
//        if (Double.compare(maxPt[index], pt) < 0) {
//            mxTeam[index] = tm;
//            maxPt[index] = pt;
//        }
//    }
//
//    public void showGraph() throws IOException {
//        for (int i = 0; i < range; i++) {
//            int cnt = (int) (4000.0 * (percentCount[i] / count));
//            //if (cnt != 0)
//            FileUtil.getInstance().writer1.write("\n(" + deno * i + "% - " + deno * (i + 1) + "%) ");
//            for (int j = 1; j <= cnt; j++) {
//                FileUtil.getInstance().writer1.write("■");
//            }
//        }
//    }
//
//    public void showGraph(PriorityQueue<FantasyTeam> t) throws IOException {
//        while (t.size() != 0) {
//            double v = t.peek().getTeamTotalAccuracy();
//            int cnt = (int) (v);
//            FileUtil.getInstance().writer1.write("\n(" + t.poll().getTotActualPt() + ")");
//            for (int j = 1; j <= cnt; j++) {
//                FileUtil.getInstance().writer1.write("■");
//            }
//        }
//    }
////    private void setCandVC(){
////        Set<String> C=new HashSet<>();
////        Random random=new Random();
////
////        for(int i=lB;i<=uB;i++){
////            while
////            mxTeam[i].first=mxTeam[i].players.get(random.nextInt(11));
////        }
////
////        while (second == null || second.getName().equals(first.getName())) {
////            section = random.nextInt(bound);
////            if (section > (3 * (bound / 10))) {
////                second = players.get(random.nextInt(thirtyFivePointMark));
////            } else {
////                second = players.get(random.nextInt(11 - thirtyFivePointMark) + thirtyFivePointMark);
////            }
////        }
////        }
////    }
//
//    private void printRange(int l,int r,BufferedWriter writer) throws IOException {
//        r=Math.min(r,range);
//        for (int i = l; i <= r; i++) {
//            writer.write("\n(" + deno * i + "% - " + deno * (i + 1) + "%)" + ((percentCount[i] / count) * 100.0) + " | " + minPt[i] + " | " + maxPt[i]);
////            if (mnTeam[i] != null)
////                FileUtil.getInstance().writer1.write("\nMINTEAM:-"+i+"%\n"+mnTeam[i].printTeam(Dr11Data.TEAM_COUNT++));
//            if (mxTeam[i] != null)
//                writer.write("\n" + i + "%\n" + mxTeam[i].printTeam(Dr11Data.TEAM_COUNT++));
//        }
//    }
//    public void printProb() throws IOException {
//
//        double rangePr = (uB - lB) / 5.0;
//
//        printRange((int)lB,(int)uB,FileUtil.getInstance().writer2);
////        printRange((int)lB,(int)(lB+rangePr),FileUtil.getInstance().writer3);
////        printRange((int)(lB+rangePr),(int)(lB+2*rangePr),FileUtil.getInstance().writer2);
////        printRange((int)(lB+2*rangePr),(int)(lB+3*rangePr),FileUtil.getInstance().writer1);
////        printRange((int)(lB+3*rangePr),(int)(lB+4*rangePr),FileUtil.getInstance().writer4);
////        printRange((int)(lB+4*rangePr),(int)(lB+5*rangePr),FileUtil.getInstance().writer5);
//    }

    public void showGraph(int v) {

        while (v >= 0) {
            FileUtil.getInstance().setStr("■");
            v /= 10;
        }
        FileUtil.getInstance().setStr("\n");
    }
}
