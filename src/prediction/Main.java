//package prediction;
//
//import Config.Dr11Data;
//
//import java.io.IOException;
//
//public class Main {
//
////    private static PriorityQueue<Player> pQueue = new PriorityQueue<Player>(new PlayerComparator());
////
////    static class PlayerComparator implements Comparator<Player> {
////
////        @Override
////        public int compare(Player t1, Player t2) {
////            return -Double.compare(t1.lr1.getAccuracy(), t2.lr1.getAccuracy());
////        }
////    }
////
////    private static void print() {
////        for (Player p : Dr11Data.getInstance().wicket) {
////            pQueue.add(p);
////        }
////
////        for (Player p : Dr11Data.getInstance().bat) {
////            pQueue.add(p);
////        }
////
////        for (Player p : Dr11Data.getInstance().allRounder) {
////            pQueue.add(p);
////        }
////
////        for (Player p : Dr11Data.getInstance().bowl) {
////            pQueue.add(p);
////        }
////        int size = pQueue.size();
////        int ct = 0;
////        while (pQueue.size() != 0) {
////            Player p = pQueue.poll();
////            ct++;
////            double percent = ((double) ct) / ((double) size);
////
////            if (Double.compare(percent, 75.0) <= 0) {
////                Dr11Data.SEVENTY_FIVE = p.getActualAvgPt();
////            }
////            if (Double.compare(percent, 25.0) <= 0) {
////                Dr11Data.TWENTY_FIVE = p.getActualAvgPt();
////            }
////        }
////
////        for (Player p : Dr11Data.getInstance().wicket) {
////            pQueue.add(p);
////        }
////
////        for (Player p : Dr11Data.getInstance().bat) {
////            pQueue.add(p);
////        }
////
////        for (Player p : Dr11Data.getInstance().allRounder) {
////            pQueue.add(p);
////        }
////
////        for (Player p : Dr11Data.getInstance().bowl) {
////            pQueue.add(p);
////        }
////        while (pQueue.size() != 0) {
////            Player p = pQueue.poll();
////            System.out.println(p.getName() + " | " + p.getAvgPt() + " | " + p.getActualAvgPt() + " | " + p.getResult() + " | " + (int) (p.lr1.getAccuracy() * 100) + "%" + " | " + p.lr1.getPredictionStr());
////        }
////    }
//
//    public static void main(String[] args) throws IOException {
//        // write your code here
//        System.out.println("version" + 3);
//        //print();
////        if (Dr11Data.SAFE) {
////            Teams teams = new Teams(null);
////            teams.showTeams(30);
////
////        } else
////            for (TeamArrangement ta : Dr11Data.getInstance().config) {
////                Teams teams = new Teams(ta);
////                teams.showTeams(3);
////            }
////        FantasyTeams teams=new FantasyTeams(null);
////        teams.showTeams(100);
////        Analysis.getInstance().printProb();
////        //Analysis.getInstance().showGraph();
////        FileUtil.close();
//    }
//}
