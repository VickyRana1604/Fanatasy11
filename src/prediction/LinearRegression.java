//package prediction;
//
//public class LinearRegression {
//    private double m;
//    private double c;
//    private double accuracy;
//    private double maxPt;
//    private double minPt;
//    private int upperProb;
//    private int lowerProb;
//    double xs, yS, x2S, xyS;
//    private String s;
//
//    int argSize;
//
//    public double getAccuracy() {
//        return accuracy;
//    }
//
//    public LinearRegression(Double[] args,int MATCH_NO) {
//
//        int LEN = MATCH_NO-1;//args.length;
//        minPt = 500.0;
//        maxPt = -500.0;
//
//        xs = x2S = yS = xyS = 0;
//        argSize = 0;
//        for (int i = 0; i < LEN; i++) {
//            if (Double.compare(args[i], 0.0) != 0) {
//                xs += i + 1;
//                x2S += ((double) (i + 1)) * ((double) (i + 1));
//                yS += args[i];
//                xyS += args[i] * ((double) (i + 1));
//                argSize++;
//
//                minPt = Math.min(minPt, args[i]);
//                maxPt = Math.max(maxPt, args[i]);
//            }
//        }
//
//
//        double count = argSize;
//        m = xs * yS - xyS * count;
//        m /= xs * xs - x2S * count;
//        c = yS - m * xs;
//        c /= count;
//
//        upperProb = lowerProb = 0;
//        accuracy = 0.0;
//        double sqMean = 0.0;
//        double yM = yS / ((double) argSize);
//        s += "";
//        for (int i = 0; i < LEN; i++) {
//            if (Double.compare(args[i], 0.0) != 0) {
//                double y = args[i];
//                double pY = predict(i + 1);
//                sqMean += (y - yM) * (y - yM);
//                accuracy += (y - pY) * (y - pY);
//                s += "(" + (int) pY + " | " + (int) y + "), ";
//                if (Double.compare(args[i], y) >= 0) {
//                    upperProb += 1;
//                } else {
//                    lowerProb += 1;
//                }
//            }
//        }
////        upperProb = (upperProb * 10000) / argSize;
////        lowerProb = (lowerProb * 10000) / argSize;
//        accuracy /= sqMean;
//    }
//
//    public String getPredictionStr() {
//        return s;
//    }
//
//    public double predict(double x) {
//        return m * x + c;
//    }
//}
