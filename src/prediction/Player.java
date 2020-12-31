//package prediction;
//
//import Config.Config;
//
//class Player {
//    private String name;
//    // private final double[] WT = {1.1, 1.2, 1.3, 1.5, 1.7, 2.11, 2.55,2.6};
//    //private final double[] WT = {1.1, 1.2, 1.3, 1.4, 1.6, 1.7, 1.8, 1.9};
//    //private final double[] WT = {1, 1, 1, 1, 1, 1, 1,1};
//    //private static final double[] WT = {1.05, 1.1, 1.15, 1.25, 1.35, 1.45, 1.6, 1.75};
//
//    private double result;
//    private double cr;
//    private boolean isBlack;
//    private double wt;
//    private double actualAvgPt;
//    private double avgPt;
//    private double pY;
//    private double accuracy;
//    public int MATCH_NO = 11;
//    public LinearRegression lr1;
//    public LinearRegression lrD;
//
//
//    public double getWt() {
//        return wt;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getResult() {
//        return result;
//    }
//
//    public double getAccuracy() {
//        return Double.isNaN(accuracy) ? 0 : accuracy;
//    }
//
//    public double getCr() {
//        return cr;
//    }
//
//    public boolean isBlack() {
//        return isBlack;
//    }
//
//    public double getAvgPt() {
//        return (int) Math.max(-4, pY);
//    }
//
//    public double getCalibratedAvgPt() {
//        if (!Config.SAFE) {
//
//            int rNo = Config.random.nextInt(Config.bound);
//            if (Double.compare(actualAvgPt, Config.TWENTY_FIVE) >= 0) {
//
//                if (Double.compare(rNo, (Config.bound * 0.2)) <= 0) {
//                    return actualAvgPt - (actualAvgPt * Math.min(0.35, wt / 3.0));
//                }
//
//            } else if (Double.compare(actualAvgPt, Config.TWENTY_FIVE) < 0 && Double.compare(actualAvgPt, Config.SEVENTY_FIVE) >= 0) {
//                if (Double.compare(rNo, (Config.bound * 0.35)) > 0 && Double.compare(rNo, (Config.bound * 0.50)) <= 0) {
//                    return actualAvgPt + (actualAvgPt * Math.min(0.20, wt / 4.0));
//                } else if (Double.compare(rNo, (Config.bound * 0.65)) > 0 && Double.compare(rNo, (Config.bound * 0.8)) <= 0) {
//                    return actualAvgPt - (actualAvgPt * Math.min(0.20, wt / 4.0));
//                }
//
//            } else if (Double.compare(actualAvgPt, Config.SEVENTY_FIVE) < 0) {
//                if (Double.compare(rNo, (Config.bound * 0.8)) > 0 && Double.compare(rNo, (Config.bound)) < 0) {
//                    return actualAvgPt + (actualAvgPt * Math.min(2.5, wt * 3.0));
//                }
//            }
//        }
//        return actualAvgPt;
//    }
//
//    public double getActualAvgPt() {
//        return actualAvgPt;
//    }
//
//    public Player(Player p) {
//        this.result = p.result;
//        this.name = p.name;
//        this.cr = p.cr;
//        this.isBlack = p.isBlack;
//        this.actualAvgPt = p.actualAvgPt;
//        this.avgPt = p.avgPt;
//        this.pY = p.pY;
//        this.accuracy = p.accuracy;
//        this.MATCH_NO = p.MATCH_NO;
//    }
//
//
//    public Player(String name, double cr, boolean isBlack, Double... dr11Pts) {
//
//        MATCH_NO = isBlack ? Config.T : Config.F;
//        this.name = name;
//        this.cr = cr;
//        this.isBlack = isBlack;
//        lr1 = new LinearRegression(dr11Pts,MATCH_NO);
//        pY = lr1.predict(MATCH_NO);
//        accuracy = lr1.getAccuracy();
//
//        //method1(dr11Pts);
//        //this.result = dr11Pts[MATCH_NO - 1];
//    }
//
//    private void method1(Double... dr11Pts) {
//        double totPt = 0.0;
//        double totDen = 0.0;
//        int argsSize = 0;
//
//        Double[] args = new Double[MATCH_NO - 1];
//        for (int i = 0; i < (MATCH_NO - 1); i++)
//            if (Double.compare(dr11Pts[i], 0.0) != 0)
//                args[argsSize++] = dr11Pts[i];
//
//
//        wt = 1.0;
//        int ct = 0;
//        for (int i = 0; i < argsSize; i++) {
//            ct++;
//            wt += 0.5;
//
//            if ((i >= 1) && Double.compare(args[i - 1] - args[i], 25.0) == 1) {
//                double temp = (int) ((args[i - 1] - args[i]) / 25.0);
//                wt -= 0.1 * temp;
//            }
//
//            if ((i >= 2) &&
//                    Double.compare(args[i - 1], args[i]) == 1 &&
//                    Double.compare(args[i - 2], args[i - 1]) == 1 &&
//                    Double.compare(args[i - 2] - args[i], 40.0) == 1) {
//                double temp = (int) ((args[i - 2] - args[i]) / 40.0);
//                wt -= 0.07 * temp;
//            }
//
//            if (Double.compare(args[i], 100.0) == 1)
//                wt += 0.5;
//            else if (Double.compare(args[i], 80.0) == 1)
//                wt += 0.4;
//            else if (Double.compare(args[i], 60.0) == 1)
//                wt += 0.3;
//            else if (Double.compare(args[i], 40.0) == 1)
//                wt += 0.2;
//            else if (Double.compare(args[i], 25.0) == 1)
//                wt += 0.1;
//
//            if (Double.compare(args[i], 0.0) == -1)
//                wt -= 0.5;
//            if (Double.compare(args[i], 0.0) == -1)
//                wt -= 0.4;
//            else if (Double.compare(args[i], 7.0) == -1)
//                wt -= 0.3;
//            else if (Double.compare(args[i], 12.0) == -1)
//                wt -= 0.2;
//            else if (Double.compare(args[i], 15.0) == -1)
//                wt -= 0.1;
//
//            if ((MATCH_NO - i) >= 2)
//                wt += 0.3;
//
//            if ((i >= 1) && Double.compare(args[i] - args[i - 1], 25.0) == 1) {
//                double temp = (int) ((args[i] - args[i - 1]) / 25.0);
//                wt += 0.1 * temp;
//            }
//
//            if ((i >= 2) && Double.compare(args[i], args[i - 1]) == 1 && Double.compare(args[i - 1], args[i - 2]) == 1 && Double.compare(args[i] - args[i - 2], 40.0) == 1) {
//                double temp = (int) ((args[i] - args[i - 2]) / 40.0);
//                wt += 0.07 * temp;
//            }
//
//            totPt += wt * args[i];
//            totDen += wt;
//
//        }
//
//        actualAvgPt = totPt / totDen;
//        actualAvgPt = (Math.round(actualAvgPt) * 10) / 10;
//        avgPt = getCalibratedAvgPt();
//        wt /= (double) ct;
//    }
//
//}
