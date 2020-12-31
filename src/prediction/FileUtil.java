//package prediction;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class FileUtil {
//
//    private static FileUtil util;
//    public static FileUtil getInstance() {
//        if (util == null) {
//            try {
//                util = new FileUtil();
//            } catch (Exception e) {
//            }
//        }
//        return util;
//    }
//
//    public BufferedWriter writer1,writer2,writer3,writer4,writer5;
//    public FileUtil() throws IOException {
//        writer1=new BufferedWriter(new FileWriter("D:/teams/VICKY.txt"));
//        writer2=new BufferedWriter(new FileWriter("D:/teams/GG.txt"));
//        writer3=new BufferedWriter(new FileWriter("D:/teams/ARUN.txt"));
//        writer4=new BufferedWriter(new FileWriter("D:/teams/AKASH.txt"));
//        writer5=new BufferedWriter(new FileWriter("D:/teams/RUBEL.txt"));
//    }
//    public  static void close() throws IOException {
////        FileUtil.getInstance().writer1.flush();
////        FileUtil.getInstance().writer2.flush();
////        FileUtil.getInstance().writer3.flush();
////        FileUtil.getInstance().writer4.flush();
////        FileUtil.getInstance().writer5.flush();
//
//        FileUtil.getInstance().writer1.close();
//        FileUtil.getInstance().writer2.close();
//        FileUtil.getInstance().writer3.close();
//        FileUtil.getInstance().writer4.close();
//        FileUtil.getInstance().writer5.close();
//    }
//}
