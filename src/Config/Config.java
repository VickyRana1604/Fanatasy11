package Config;

import prediction.TeamArrangement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Config {
    public static final boolean isFirstMatch = true;

    public static String TEAM1_NAME;

    public static String TEAM2_NAME;

    public static boolean isDataPrepared = false;
    public static final boolean startFindingTeam = true;
    public static boolean isLineupAnnounced;
    public static int leagueNo = 1;
    public static final int indVariableCount = 1;

    public final List<TeamArrangement> teamArrangements = new ArrayList();
    private static Config config = null;
    public static double TWENTY_FIVE;
    public static double SEVENTY_FIVE;
    public static boolean SAFE = false;
    public static final Random random = new Random();
    public static final int bound = 1000000000;
    public static int TEAM_COUNT = 0;


    public static int T = 16;
    public static int F = 17;

    private void insertCriConfig() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 3; j <= 6; j++) {
                for (int k = 1; k <= 4; k++) {
                    for (int l = 3; l <= 6; l++) {
                        if ((i + j + k + l) == 11)
                            teamArrangements.add(new TeamArrangement(i, j, k, l));
                    }
                }
            }
        }
    }

    private void insertFooConfig() {
        for (int i = 1; i <= 1; i++) {
            for (int j = 3; j <= 5; j++) {
                for (int k = 3; k <= 5; k++) {
                    for (int l = 1; l <= 3; l++) {
                        if ((i + j + k + l) == 11)
                            teamArrangements.add(new TeamArrangement(i, j, k, l));
                    }
                }
            }
        }
    }


    private Config() {
//        insertDubaiTeamConfig();
//        insertKXIP(true);
//        insertSRH(false);
        //insertFooConfig();
     insertCriConfig();
     //  teamArrangements.add(new TeamArrangement(1,3,1,6));
        //   insertSarjahTeamConfig();
        //insertDubaiTeamConfig();
        //insertMI(true);
        //insertDC(false);
//
//        insertKKR(true);
//        insertDC(false);
//        insertDubaiTeamConfig();
//        insertCSK(true);
//        insertRCB(false);
//        insertSarjahTeamConfig();
//        insertKXIP(true);
//        insertKKR(false);
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    //motera
//    2315
//    1316


    private void insertSarjahTeamConfig() {
        teamArrangements.add(new TeamArrangement(2, 4, 1, 4));
        teamArrangements.add(new TeamArrangement(3, 4, 1, 3));
        teamArrangements.add(new TeamArrangement(2, 5, 1, 3));
        teamArrangements.add(new TeamArrangement(2, 3, 3, 3));
        teamArrangements.add(new TeamArrangement(1, 3, 4, 3));

        teamArrangements.add(new TeamArrangement(2, 4, 2, 3));
        teamArrangements.add(new TeamArrangement(2, 3, 2, 4));
        teamArrangements.add(new TeamArrangement(3, 3, 1, 4));
        teamArrangements.add(new TeamArrangement(2, 3, 1, 5));
        teamArrangements.add(new TeamArrangement(1, 4, 3, 3));
        teamArrangements.add(new TeamArrangement(1, 5, 2, 3));

        teamArrangements.add(new TeamArrangement(3, 3, 2, 3));
        teamArrangements.add(new TeamArrangement(1, 6, 1, 3));
        teamArrangements.add(new TeamArrangement(1, 3, 3, 4));
        teamArrangements.add(new TeamArrangement(1, 4, 2, 4));
        teamArrangements.add(new TeamArrangement(1, 5, 1, 4));
    }

    private void insertSheikZayedTeamConfig() {
        teamArrangements.add(new TeamArrangement(2, 3, 2, 4));
        teamArrangements.add(new TeamArrangement(2, 4, 2, 3));
        teamArrangements.add(new TeamArrangement(2, 4, 1, 4));
        teamArrangements.add(new TeamArrangement(2, 3, 1, 5));
        teamArrangements.add(new TeamArrangement(1, 3, 2, 5));
        teamArrangements.add(new TeamArrangement(3, 3, 2, 3));
        teamArrangements.add(new TeamArrangement(3, 3, 1, 4));

        teamArrangements.add(new TeamArrangement(2, 3, 3, 3));

        teamArrangements.add(new TeamArrangement(2, 5, 1, 3));
        teamArrangements.add(new TeamArrangement(1, 4, 2, 4));
        teamArrangements.add(new TeamArrangement(1, 4, 1, 5));
        teamArrangements.add(new TeamArrangement(1, 4, 3, 3));

        teamArrangements.add(new TeamArrangement(1, 3, 3, 4));
    }


    private void insertDubaiTeamConfig() {
        teamArrangements.add(new TeamArrangement(1, 5, 4, 1));
        teamArrangements.add(new TeamArrangement(1, 4, 5, 1));
        teamArrangements.add(new TeamArrangement(1, 3, 5, 2));
        teamArrangements.add(new TeamArrangement(1, 5, 3, 2));
        teamArrangements.add(new TeamArrangement(1, 4, 4, 2));
        teamArrangements.add(new TeamArrangement(1, 3, 4, 3));
        teamArrangements.add(new TeamArrangement(1, 4, 3, 3));
//        teamArrangements.add(new TeamArrangement(1, 3, 2, 5));
//        teamArrangements.add(new TeamArrangement(1, 3, 3, 4));
//        teamArrangements.add(new TeamArrangement(1, 3, 2, 5));
//        teamArrangements.add(new TeamArrangement(1, 3, 1, 6));
//        teamArrangements.add(new TeamArrangement(1, 5, 2, 3));
//        teamArrangements.add(new TeamArrangement(1, 4, 1, 5));
//        teamArrangements.add(new TeamArrangement(2, 3, 2, 4));
//        teamArrangements.add(new TeamArrangement(1, 4, 3, 3));
//        teamArrangements.add(new TeamArrangement(2, 3, 1, 5));
//        teamArrangements.add(new TeamArrangement(2, 3, 3, 3));
//        teamArrangements.add(new TeamArrangement(2, 4, 2, 3));
//        teamArrangements.add(new TeamArrangement(2, 4, 1, 4));
//        teamArrangements.add(new TeamArrangement(2, 5, 1, 3));
//        teamArrangements.add(new TeamArrangement(3, 3, 1, 4));
//        teamArrangements.add(new TeamArrangement(3, 3, 2, 3));
    }
//    private void insertMI(boolean ft) {
//        wicket.add(new Player("Q de Kock", 10, ft, 42.0, 33.0, 19.0, 10.0, 99.0, 56.0, 75.0, 121.0, 80.0, 95.0, 12.0, 36.0, 60.0,35.0,67.0));
//        wicket.add(new Player("I Kishan", 9, ft, 0.0, 0.0, 131.0, 35.0, 48.0, 2.0, 38.0, 4.0, 12.0, 96.0, 47.0, 34.0, 98.0,42.0,77.0));
//
//        bat.add(new Player("R Sharma", 9.5, ft, 18.0, 107.0, 22.0, 96.0, 20.0, 63.0, 3.0, 46.0, 23.0, 0.0, 0.0, 0.0, 0.0,8.0,10.0));
//        bat.add(new Player("S Yadav", 9, ft, 23.0, 59.0, 2.0, 32.0, 37.0, 106.0, 79.0, 23.0, 2.0, 20.0, 50.0, 115.0, 25.0,45.0,81.0));
//        bat.add(new Player("H Pandya", 9.0, ft, 22.0, 50.0, 21.0, 41.0, 46.0, 46.0, 2.0, 30.0, 14.0, 4.0, 88.0, 25.0, 0.0,0.0,51.0));
//        //bat.add(new Player("S Tiwary", 8.0, ft, 51.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0, 44.0, 17.0, 4.0,5.0,0.0));
//
//        allRounder.add(new Player("K Pollard", 8.5, ft, 25.0, 51.0, 101.0, 62.0, 43.0, 39.0, 16.0, 12.0, 47.0, 4.0, 18.0, 34.0, 4.0,55.0,25.0));
//        allRounder.add(new Player("K Pandya", 8.0, ft, 30.0, 5.0, 4.0, 29.0, 55.0, 14.0, 76.0, 6.0, 46.0, 14.0, 5.0, 15.0, 24.0,-4.0,46.0));
//
//        bowl.add(new Player("J Bumrah", 9.5, ft, 30.0, 54.0, 0.0, 58.0, 50.0, 114.0, 4.0, 31.0, 81.0, 54.0, 2.0, 93.0, 95.0,0.0,126.0));
//        bowl.add(new Player("T Boult", 9.0, ft, 29.0, 62.0, 54.0, 33.0, 54.0, 54.0, 35.0, 29.0, 6.0, 124.0, 2.0, 35.0, 81.0,0.0,66.0));
//        bowl.add(new Player("N Coulter-Nile", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 23.0,40.0,29.0,0.0,0.0,29.0,5.0,4.0));
//        //bowl.add(new Player("J pattinson", 8.5, ft, 50.0, 54.0, -2.0, 54.0, 54.0, 56.0, -2.0, 0.0,0.0,0.0,48.0,6.0,0.0,6.0,0.0));
//        //bowl.add(new Player("R Chahar", 8.5, ft, 37.0, 54.0, 29.0, 54.0, 12.0, 29.0, 10.0, 58.0, 54.0, 56.0, -2.0, 33.0, 31.0,2.0,6.0));
//        bowl.add(new Player("J yadav", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 15.0, 6.0,12.0,19.0));
//        //bowl.add(new Player("D kulkarni", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0,7.0,0.0));
//    }
//
//    private void insertDC(boolean ft) {
//        wicket.add(new Player("R Pant", 8.5, ft, 47.0, 68.0, 45.0, 57.0, 64.0, 17.0, 0.0, 0.0, 0.0, 39.0, 43.0,45.0,27.0,27.0,7.0,6.0));
//        //wicket.add(new Player("A Carey", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 26.0, 20.0, 16.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
//
//        bat.add(new Player("S Iyer", 9.0, ft, 57.0, 31.0, 23.0, 119.0, 24.0, 38.0, 59.0, 72.0, 30.0, 20.0, 56.0,15.0,32.0,19.0,19.0,26.0));
//        bat.add(new Player("S dhawan", 10.0, ft, 2.0, 44.0, 42.0, 44.0, 47.0, 18.0, 89.0, 79.0, 145.0, 144.0, 11.0,2.0,2.0,72.0,10.0,100.0));
//        //bat.add(new Player("p shaw", 8.0, ft, 26.0, 87.0, 6.0, 90.0, 55.0, 27.0, 17.0, 2.0, 2.0, 10.0,0.0,0.0,16.0,23.0,2.0,0.0));
//        bat.add(new Player("S hetmyer", 8.0, ft, 16.0, 12.0, 29.0, 21.0, 25.0, 84.0, 0.0, 0.0, 0.0, 16.0, 16.0,23.0,16.0,0.0,0.0,52.0));
//        bat.add(new Player("a rahane", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 22.0, 14.0, 13.0, 0.0, 10.0,35.0,0.0,93.0,2.0,4.0));
//
//        allRounder.add(new Player("M stoinis", 9.5, ft, 126.0, 10.0, 16.0, 24.0, 83.0, 101.0, 38.0, 23.0, 33.0, 13.0, 56.0,10.0,6.0,24.0,114.0,132.0));//chng 9.5
//        allRounder.add(new Player("A patel", 8.5, ft, 41.0, 39.0, 17.0, 0.0, 58.0, 62.0, 37.0, 49.0, 33.0, 29.0, 23.0,11.0,0.0,4.0,52.0,37.0));
//        //allRounder.add(new Player("D sams", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 12.0,0.0,0.01,0.0,0.0,2.0,14.0,0.0));
//
//        bowl.add(new Player("K rabada", 9.5, ft, 62.0, 87.0, 82.0, 23.0, 114.0, 87.0, 62.0, 29.0, 37.0, 54.0, 72.0,1.0,16.0,54.0,25.0,120.0));
//        bowl.add(new Player("R ashwin", 8.5, ft, 58.0, 0.0, 0.0, -2.0, 37.0, 56.0, 29.0, 41.0, 2.0, 35.0, 14.0,31.0,22.0,33.0,79.0,0.01));
//        //bowl.add(new Player("T deshpande", 7.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 52.0, 27.0, -2.0, 11.0));
//        //bowl.add(new Player("H Patel", 8.0, ft, 0.0,0.0, 0.0, 62.0, 0.01, 46.0, 2.0,0.0, 0.0,0.0,0.0,0.0, 10.0,0.0,0.0,0.0));
//        bowl.add(new Player("P dubey", 7.5, ft,  0.0,0.0, 0.0,0.0,0.0, 0.0,0.0, 0.0,0.0,0.0,0.0, 0.0, 9.0, 13.0, 20.0, 12.0));
//        bowl.add(new Player("A nortje", 9.0, ft, 7.0, 56.0, 13.0, 87.0, 56.0, 29.0, 4.0, 54.0, 58.0, 0.0, 54.0,28.0,25.0,79.0,23.0,10.0));
//    }
//
//    private void insertSRH(boolean ft) {
//        //wicket.add(new Player("J Bairstow", 9.5, ft, 101.0, 5.0, 85.0, 8.0, 43.0, 136.0, 38.0, 37.0, 47.0, 17.0, 39.0, 0.0, 0.0,0.0));
//        wicket.add(new Player("S Goswami", 7.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,10.0,2.0));
//        //wicket.add(new Player("W Saha", 8.5, ft, 0.0, 45.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 115.0, 49.0,99.0,0.0));
//
//        bat.add(new Player("D Warner", 10.5, ft, 11.0, 52.0, 56.0, 43.0, 81.0, 85.0, 59.0, 19.0, 56.0, 17.0, 62.0, 106.0, 22.0,117.0,32.0,6.0));
//        bat.add(new Player("K Williamson", 9.0, ft, 0.0, 0.0, 50.0, 12.0, 1.0, 35.0, 30.0, 84.0,39.0,0.0,0.0,24.0,24.0,12.0,68.0,92.0));
//        bat.add(new Player(" M Pandey", 9, ft, 49.0, 70.0, 15.0, 38.0, 48.0, 5.0, 74.0, 17.0, 18.0, 123.0, 15.0, 54.0, 43.0,4.0,33.0,36.0));
//        bat.add(new Player("P Garg", 8, ft, 17.0, 4.0, 12.0, 77.0, 12.0, 22.0, 22.0, 21.0, 24.0, 4.0, 7.0, 0.0, 0.0,12.0,15.0,25.0));
//        bat.add(new Player("A Samad", 8, ft, 0.0, 0.0, 27.0, 41.0, 23.0, 13.0, 0.0, 0.0, 31.0, 4.0, 12.0, 12.0, 4.0,4.0,20.0,43.0));
//
//
//        allRounder.add(new Player("J Holder", 9.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 52.0, 45.0, 70.0, 91.0, 59.0, 23.0, 87.0,57.0,106.0,35.0));
//        //allRounder.add(new Player("V Shankar", 8.0, ft, 27.0, 0.0, 0.0, 0.0, 0.0, 0.0, 10.0, 18.0, 44.0, 97.0, 46.0,29.0,0.0,0.0));
//        //allRounder.add(new Player("A Sharma", 8.0, ft, 37.0, 6.0, 5.0, 41.0, 14.0, 44.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 22.0,0.0));
//
//        //allRounder.add(new Player("A samad", 8.0, ft, 0.0, 0.0, 27.0, 41.0, 23.0, 13.0, 0.0, 0.0, 31.0, 4.0, 12.0));
//
//        bowl.add(new Player("R Khan", 9.5, ft, 19.0, 29.0, 85.0, 10.0, 42.0, 93.0, 54.0, 21.0, 30.0, 31.0, 68.0, 85.0, 31.0,29.0,18.0,43.0));
//        bowl.add(new Player("T Natarajan", 8.5, ft, 32.0, 27.0, 29.0, 50.0, 12.0, 62.0, 18.0, 50.0, 52.0, -2.0, 6.0, 54.0, 43.0,2.0,54.0,4.0));
//        bowl.add(new Player("S Nadeem", 8, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 12.0,0.0,29.0,29.0,58.0,29.0,0.01));
//        bowl.add(new Player("S Sharma", 8.5, ft, 12.0, 0.0, 0.0, 0.0, 50.0, 4.0, 4.0, 59.0, 4.0, 4.0, 54.0, 54.0, 56.0,79.0,6.0,31.0));
//        //bowl.add(new Player("K Ahmed", 8.0, ft, 0.0, 27.0, 25.0, 4.0, 0.0, 60.0, 52.0, 48.0, 0.0, 0.0, 12.0,0.0,0.0,0.0,0.0));
//        //bowl.add(new Player("B thampi", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,23.0,0.0,0.0,0.0,0.0,0.0,0.0));
//    }
//
//    private void insertKKR(boolean ft) {
//        wicket.add(new Player("D Karthik", 8.5, ft, 39.0, 8.0, 21.0, 10.0, 25.0, 82.0, 5.0, 17.0, 39.0, 10.0, 7.0));
//        //wicket.add(new Player("N naik", 8.0, ft, 13.0,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0,0.0));
//
//        bat.add(new Player("E Morgan", 9.5, ft, 29.0, 53.0, 43.0, 67.0, 18.0, 32.0, 11.0, 49.0, 43.0, 39.0, 33.0));
//        bat.add(new Player("S Gill", 9.5, ft, 10.0, 91.0, 66.0, 44.0, 24.0, 82.0, 43.0, 27.0, 61.0, 5.0, 23.0));
//        bat.add(new Player(" N Rana", 8.5, ft, 32.0, 36.0, 30.0, 82.0, 14.0, 14.0, 12.0, 10.0, 46.0, 2.0, 108.0));
//        bat.add(new Player("R Tripathi", 8.0, ft, 0.0, 0.0, 0.0, 49.0, 107.0, 3.0, 21.0, 12.0, 31.0, 5.0, 42.0));
//        //bat.add(new Player("T Banton", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0, 17.0,0.0));
//
//        //allRounder.add(new Player("A Russell", 9.5, ft, 42.0, 37.0, 34.0, 70.0, 29.0, 10.0, 43.0, 19.0,16.0,0.0,0.0));
//        allRounder.add(new Player("S Narine", 9.5, ft, 48.0, 2.0, 54.0, 1.0, 49.0, 54.0, 0.0, 0.0, 0.0, 0.0, 88.0));
//
//        //bowl.add(new Player("K Yadav", 8.5, ft, 3.0, 4.0, 29.0, 0.0, 0.0, 0.0, 0.0, 0.0, 6.0, 17.0,0.0));
//        bowl.add(new Player("P Cummins", 8.5, ft, 56.0, 39.0, 46.0, 4.0, 24.0, 9.0, 3.0, 72.0, 29.0, 22.0, 83.0));
//        bowl.add(new Player("P Krishna", 8, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 79.0, 27.0, -2.0, 0.0, 4.0, 2.0));
//        bowl.add(new Player("L Ferguson", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 85.0, 53.0, 29.0));
//        bowl.add(new Player("V Chakravarthy", 8.5, ft, 0.0, 37.0, 62.0, 31.0, 30.0, 4.0, 11.0, 31.0, 29.0, 4.0, 147.0));
//        //bowl.add(new Player("S Mavi", 8.5, ft, 72.0, 4.0, 64.0, 7.0, 25.0, 0.0, 0.0, 29.0,23.0,0.0,0.0));
//        bowl.add(new Player("K Nagarkoti", 8.0, ft, 0.0, 4.0, 79.0, 26.0, 29.0, -2.0, 6.0, 0.0, 0.0, 0.0, 14.0));
//        //bowl.add(new Player("C Green", 8, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0,0.0,0.0,0.0));
//    }
//
//    private void insertKXIP(boolean ft) {
//        wicket.add(new Player("rahul", 11, ft, 49.0, 180.0, 98.0, 22.0, 84.0, 13.0, 92.0, 100.0, 102.0, 30.0, 51.0, 52.0));
//        wicket.add(new Player("N pooran", 9.5, ft, 8.0, 22.0, 44.0, 55.0, 44.0, 116.0, 36.0, 20.0, 42.0, 77.0, 46.0, 11.0));
//        //wicket.add(new Player("S singh", 7.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 17.0, 28.0,0.0,0.0,0.0,0.0,0.0));
//
//        //bat.add(new Player("m agarwal", 9.5, ft, 124.0, 42.0, 158.0, 32.0, 33.0, 14.0, 76.0, 59.0, 24.0, 25.0,0.0,0.0));
//        bat.add(new Player("C Gayle", 9, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 76.0, 33.0, 40.0, 28.0, 83.0));
//        bat.add(new Player("D Hooda", 8, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 12.0, 38.0, 18.0, 2.0, 4.0));
//        bat.add(new Player("M singh", 8, ft, 0.0, 0.0, 0.0, 0.0, 35.0, 10.0, 8.0, 0.0, 0.0, 0.0, 30.0, 90.0));
//        //bat.add(new Player("S khan", 8, ft,18.0,12.0,12.0,12.0,20.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0,0.0));
//        //bat.add(new Player("K Nair", 8, ft,5.0,21.0,4.0,10.0,0.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0,0.0));
//
//        //allRounder.add(new Player("J Neesham", 8.5, ft, 0.0, 4.0, 27.0, 13.0, 0.0, 0.0, 0.0, 0.0, 0.0, 41.0,0.0,0.0));
//        allRounder.add(new Player("G maxwell", 8.5, ft, 5.0, 36.0, 17.0, 13.0, 16.0, 17.0, 24.0, 4.0, 4.0, 72.0, 16.0, 25.0));
//
//        bowl.add(new Player("M shami", 9.0, ft, 93.0, 33.0, 81.0, 41.0, 2.0, 27.0, 35.0, 48.0, 54.0, 54.0, 29.0, 79.0));
//        bowl.add(new Player("C jordan", 8.5, ft, 19.0, 0.0, 0.0, 0.0, -2.0, 0.0, 2.0, 37.0, 40.0, 0.0, 106.0, 54.0));
//        //bowl.add(new Player("M rahman", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 3.0, 0.01,0.0,0.0,0.0,0.0,0.0));
//        //bowl.add(new Player("S Cottrell", 8.5, ft, 56.0, 56.0, 23.0, 39.0, 2.0, 0.01, 0.0,0.0,0.0,0.0,0.0,0.0));
//        bowl.add(new Player("R bishnoi", 8.5, ft, 31.0, 95.0, 4.0, 3.0, 4.0, 84.0, 29.0, 2.0, 31.0, 4.0, 47.0, 64.0));
//        bowl.add(new Player("A singh", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 70.0, 37.0, 27.0, 48.0, 2.0, 81.0, 2.0));
//        bowl.add(new Player("M Ashwin", 8.5, ft, 0.0, 79.0, 29.0, 0.0, 0.0, 0.0, 0.0, 56.0, 18.0, 29.0, 41.0, 37.0));
//    }
//
//    private void insertRR(boolean ft) {
//        wicket.add(new Player("J buttler", 9.0, ft, 0.0, 8.0, 38.0, 31.0, 104.0, 27.0, 29.0, 31.0, 31.0, 109.0, 13.0, 4.0));
//        wicket.add(new Player("S samson", 9.0, ft, 145.0, 123.0, 21.0, 9.0, 10.0, 9.0, 41.0, 33.0, 15.0, 16.0, 45.0, 76.0));
//
//        bat.add(new Player("S smith", 9.0, ft, 93.0, 73.0, 7.0, 10.0, 11.0, 40.0, 9.0, 5.0, 77.0, 32.0, 25.0, 18.0));
//        bat.add(new Player("R uthappa", 8.5, ft, 9.0, 15.0, 6.0, 22.0, 0.0, 0.0, 25.0, 49.0, 62.0, 8.0, 27.0, 19.0));
//        bat.add(new Player("R parag", 8.0, ft, 11.0, 2.0, 13.0, 21.0, 0.0, 0.0, 52.0, 5.0, 4.0, 4.0, 28.0, 4.0));
//
//        allRounder.add(new Player("B stokes", 9.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 10.0, 45.0, 29.0, 24.0, 38.0, 163.0));
//        allRounder.add(new Player("R tewatia", 9.0, ft, 88.0, 79.0, 45.0, 34.0, 18.0, 98.0, 65.0, 27.0, 59.0, 41.0, 6.0, 4.0));
//
//        bowl.add(new Player("J archer", 9.5, ft, 64.0, 15.0, 74.0, 52.0, 58.0, 91.0, 35.0, 100.0, 2.0, 37.0, 75.0, 62.0));
//        bowl.add(new Player("S gopal", 8.5, ft, 27.0, 8.0, 5.0, 29.0, 63.0, 0.01, 4.0, 36.0, 29.0, 35.0, 4.0, 54.0));
//        // bowl.add(new Player("J unadkat", 8.0, ft, 0.01, 2.0, 46.0, 0.01, 0.0, 0.0, 29.0, 50.0));
//        bowl.add(new Player("K tyagi", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 27.0, 31.0, 27.0, 45.0, 29.0, 29.0, -2.0, 23.0));
//        bowl.add(new Player("V Aron", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
//    }
//
//
//
//
//    private void insertRCB(boolean ft) {
//        wicket.add(new Player("A de Villiers", 10, ft, 71.0, 46.0, 87.0, 17.0, 23.0, 10.0, 108.0, 12.0, 88.0, 20.0, 47.0));
//
//        bat.add(new Player("V Kohli", 10.5, ft, 34.0, 5.0, 1.0, 95.0, 51.0, 114.0, 38.0, 55.0, 52.0, 24.0, 65.0));
//        bat.add(new Player("A Finch", 8.5, ft, 38.0, 35.0, 73.0, 22.0, 18.0, 6.0, 57.0, 28.0, 30.0, 22.0, 22.0));
//        bat.add(new Player("D Paddikal", 9, ft, 76.0, 5.0, 83.0, 99.0, 16.0, 49.0, 50.0, 31.0, 41.0, 40.0, 30.0));
//        bat.add(new Player("G Singh Maan", 8.0, ft, 0.0, 0.0, 4.0, 4.0, 0.0, 20.0, 0.0, 0.0, 24.0, 43.0, 6.0));
//
//        allRounder.add(new Player("C Morris", 9.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 103.0, 66.0, 38.0, 120.0, 30.0, 29.0));
//        allRounder.add(new Player("W Sundar", 8.5, ft, 4.0, 38.0, 35.0, 6.0, 26.0, 68.0, 56.0, 16.0, 4.0, 43.0, 10.0));
//        //allRounder.add(new Player("S Dube", 8.0, ft, 63.0, 64.0, 38.0, 4.0, 17.0, 30.0, 4.0, 31.0, 0.0, 0.0));
//        //allRounder.add(new Player("S Ahmed", 7.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 10.0, 0.0));
//        allRounder.add(new Player("M Ali", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 45.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0));
//
//        bowl.add(new Player("Y Chahal", 9.0, ft, 83.0, 30.0, 23.0, 89.0, 2.0, 29.0, 35.0, 23.0, 54.0, 60.0, 31.0));
//        //bowl.add(new Player("I Udana", 8, ft, 0.0, 0.0, 48.0, 58.0, 28.0, 29.0, 41.0, 16.0, -2.0, 4.0));
//        bowl.add(new Player("N Saini", 8.5, ft, 62.0, 9.0, 0.01, 35.0, 11.0, 8.0, 31.0, 6.0, 12.0, 29.0, 4.0));
//        bowl.add(new Player("M Siraj", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 60.0, 0.0, 37.0, -2.0, 0.0, 101.0, 6.0));
//    }
//
//    private void insertCSK(boolean ft) {
//        wicket.add(new Player("ms dhoni", 9.0, ft, 20.0, 47.0, 41.0, 71.0, 12.0, 54.0, 32.0, 29.0, 7.0, 50.0, 24.0, 26.0));
//        wicket.add(new Player("N Jagadeesan", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 41.0, 0.0, 0.0, 0.0, 2.0, 4.0));
//
//        bat.add(new Player("fap duplesis", 10.0, ft, 100.0, 107.0, 51.0, 38.0, 112.0, 40.0, 20.0, 2.0, 96.0, 15.0, 5.0, 51.0));
//        bat.add(new Player("a raydu", 9.0, ft, 95.0, 0.0, 0.0, 19.0, 12.0, 37.0, 50.0, 52.0, 66.0, 17.0, 6.0, 50.0));
//        //bat.add(new Player("s watson", 9.0, ft, 17.0, 46.0, 21.0, 5.0, 112.0, 78.0, 21.0, 53.0, 46.0, 14.0,0.0));
//        //bat.add(new Player("K jadhav", 8.0, ft, 4.0, 43.0, 33.0, 1.0, 4.0, 8.0, 0.0, 0.0, 4.0, 8.0,0.0));
//        bat.add(new Player("R Gaikwad", 8.0, ft, 0.0, 8.0, 5.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 103.0));
//
//        allRounder.add(new Player("S curran", 9.5, ft, 68.0, 101.0, 30.0, 25.0, 8.0, 74.0, 21.0, 77.0, 35.0, 29.0, 72.0, 79.0));
//        allRounder.add(new Player("R jadeja", 9.0, ft, 62.0, 3.0, 13.0, 71.0, 37.0, 44.0, 18.0, 75.0, 45.0, 43.0, 12.0, 4.0));
//        allRounder.add(new Player("M Santer", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 39.0));
//
//        //bowl.add(new Player("S thakur", 8.5, ft, 0.0, 0.0, 0.0, 29.0, 52.0, 62.0, 53.0, 39.0, 27.0, 4.0, 9.0));
//        bowl.add(new Player("D chahar", 8.5, ft, 54.0, 45.0, 2.0, 54.0, 6.0, -2.0, 40.0, 6.0, 74.0, 66.0, 4.0, 54.0));
//        //bowl.add(new Player("P chawla", 8.0, ft, 31.0, 23.0, 54.0, 29.0, 25.0, 0.0, 0.0, 4.0, 0.0, 0.01,0.0));
//        //bowl.add(new Player("K sharma", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 54.0, 4.0, 52.0, -2.0, 0.0,0.0));
//        //bowl.add(new Player("J HZlwood", 8.5, ft, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 33.0, 4.0));
//        bowl.add(new Player("I Tahir", 9.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 19.0, 4.0));
//        bowl.add(new Player("M kumar", 7.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0));
//
//    }
//
//
//    //====================================================================================================================
//
//    private void insertRCBOld(boolean ft) {
//        wicket.add(new Player("AbdV", 10, ft, 71.0, 46.0, 87.0, 17.0, 23.0, 10.0, 108.0, 12.0));
//
//        bat.add(new Player("v kohli", 10.5, ft, 34.0, 5.0, 1.0, 95.0, 51.0, 114.0, 38.0, 55.0));
//        bat.add(new Player("A finch", 9, ft, 38.0, 35.0, 73.0, 22.0, 18.0, 6.0, 57.0, 28.0));
//        bat.add(new Player("D paddikal", 8.5, ft, 76.0, 5.0, 83.0, 99.0, 16.0, 49.0, 50.0, 31.0));
//        bat.add(new Player("G singh man", 8.0, ft, 0.0, 0.0, 4.0, 4.0, 0.0, 20.0, 0.0, 0.0));
//
//        allRounder.add(new Player("C morris", 9.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 103.0, 66.0, 38.0));
//        allRounder.add(new Player("W sundar", 8.5, ft, 4.0, 38.0, 35.0, 6.0, 26.0, 68.0, 56.0, 16.0));
//        allRounder.add(new Player("S dube", 8.5, ft, 63.0, 64.0, 38.0, 4.0, 17.0, 30.0, 4.0, 31.0));
//        allRounder.add(new Player("S ahmed", 7.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, .0));
//
//        bowl.add(new Player("Y chahal", 9.0, ft, 83.0, 30.0, 23.0, 89.0, 2.0, 29.0, 35.0, 23.0));
//        bowl.add(new Player("I udana", 8.5, ft, 0.0, 0.0, 48.0, 58.0, 28.0, 29.0, 41.0, 16.0));
//        bowl.add(new Player("N saini", 8.5, ft, 62.0, 9.0, 0.01, 35.0, 11.0, 8.0, 31.0, 6.0));
//        //bowl.add(new Player("M siraj", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 60.0, 0.0, 37.0, -2.0));
//    }
////    //old data
////    private void insertRR(boolean ft) {
////        wicket.add(new Player("J buttler", 9.5, ft, 0.0, 8.0, 38.0, 31.0, 104.0, 27.0, 29.0, 31.0));
////        wicket.add(new Player("S samson", 9.5, ft, 145.0, 123.0, 21.0, 9.0, 10.0, 9.0, 41.0, 33.0));
////
////        bat.add(new Player("S smith", 9.0, ft, 93.0, 73.0, 7.0, 10.0, 11.0, 40.0, 9.0, 5.0));
////        bat.add(new Player("R uthappa", 8.5, ft, 9.0, 15.0, 6.0, 22.0, 0.0, 0.0, 25.0, 49.0));
////        bat.add(new Player("R parag", 8.0, ft, 11.0, 2.0, 13.0, 21.0, 0.0, 0.0, 52.0, 5.0));
////
////        allRounder.add(new Player("B stokes", 9.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 10.0, 45.0));
////        allRounder.add(new Player("R tewatia", 9.0, ft, 88.0, 79.0, 45.0, 34.0, 18.0, 98.0, 65.0, 27.0));
////
////        bowl.add(new Player("J archer", 9.5, ft, 64.0, 15.0, 74.0, 52.0, 58.0, 91.0, 35.0, 100.0));
////        bowl.add(new Player("S gopal", 8.5, ft, 27.0, 8.0, 5.0, 29.0, 63.0, 0.01, 4.0, 36.0));
////        bowl.add(new Player("J unadkat", 8.0, ft, 0.01, 2.0, 46.0, 0.01, 0.0, 0.0, 29.0, 50.0));
////        bowl.add(new Player("K tyagi", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 27.0, 31.0, 27.0, 45.0));
////    }
////
////
////    private void insertMI(boolean ft) {
////        wicket.add(new Player("Q de Kock", 9.5, ft, 42.0, 33.0, 19.0, 10.0, 99.0, 56.0, 75.0, 121.0, 80.0, 95.0));
////        wicket.add(new Player("I Kishan", 8.5, ft, 0.0, 0.0, 131.0, 35.0, 48.0, 2.0, 38.0, 4.0, 12.0, 96.0));
////
////        //bat.add(new Player("R Sharma", 10.0, ft, 18.0, 107.0, 22.0, 96.0, 20.0, 63.0, 3.0, 46.0, 23.0));
////        bat.add(new Player("S Yadav", 9, ft, 23.0, 59.0, 2.0, 32.0, 37.0, 106.0, 79.0, 23.0, 2.0, 20.0));
////        bat.add(new Player("H Pandya", 8.5, ft, 22.0, 50.0, 21.0, 41.0, 46.0, 46.0, 2.0, 30.0, 14.0, 4.0));
////        bat.add(new Player("S Tiwary", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0));
////
////        allRounder.add(new Player("K Pollard", 9.5, ft, 25.0, 51.0, 101.0, 62.0, 43.0, 39.0, 16.0, 12.0, 47.0, 4.0));
////        allRounder.add(new Player("K Pandya", 8.5, ft, 30.0, 5.0, 4.0, 29.0, 55.0, 14.0, 76.0, 6.0, 46.0, 14.0));
////
////        bowl.add(new Player("J Bumrah", 9.0, ft, 30.0, 54.0, 0.0, 58.0, 50.0, 114.0, 4.0, 31.0, 81.0, 54.0));
////        bowl.add(new Player("T Boult", 8.5, ft, 29.0, 62.0, 54.0, 33.0, 54.0, 54.0, 35.0, 29.0, 6.0, 124.0));
////        bowl.add(new Player("N Coulter-Nile", 8.5, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 23.0, 40.0, 29.0));
////        //bowl.add(new Player("J Pattinson", 8.5, ft, 50.0, 54.0, -2.0, 54.0, 54.0, 56.0, -2.0, 0.0, 0.0));
////        bowl.add(new Player("R Chahar", 8.5, ft, 37.0, 54.0, 29.0, 54.0, 12.0, 29.0, 10.0, 58.0, 54.0, 56.0));
////    }
////
////
////
//
////
////    private void insertCSK(boolean ft) {
////        wicket.add(new Player("ms dhoni", 9.0, ft, 20.0, 47.0, 41.0, 71.0, 12.0, 54.0, 32.0, 29.0, 7.0));
////        bat.add(new Player("fap duplesis", 10.0, ft, 100.0, 107.0, 51.0, 38.0, 112.0, 40.0, 20.0, 2.0, 96.0));
////
////        bat.add(new Player("a raydu", 9.0, ft, 95.0, 0.0, 0.0, 19.0, 12.0, 37.0, 50.0, 52.0, 66.0));
////        bat.add(new Player("s watson", 9.0, ft, 17.0, 46.0, 21.0, 5.0, 112.0, 78.0, 21.0, 53.0, 46.0));
////        bat.add(new Player("K jadhav", 9.0, ft, 4.0, 43.0, 33.0, 1.0, 4.0, 8.0, 0.0, 0.0, 4.0));
////
////        allRounder.add(new Player("S curran", 9.0, ft, 68.0, 101.0, 30.0, 25.0, 8.0, 74.0, 21.0, 77.0, 35.0));
////        allRounder.add(new Player("R jadeja", 9.0, ft, 62.0, 3.0, 13.0, 71.0, 37.0, 44.0, 18.0, 75.0, 45.0));
////        allRounder.add(new Player("D bravo", 8.5, ft, 0.0, 0.0, 0.0, 4.0, 2.0, 77.0, 9.0, 72.0, 29.0));
////
////        bowl.add(new Player("S thakur", 8.5, ft, 0.0, 0.0, 0.0, 29.0, 52.0, 62.0, 53.0, 39.0, 27.0));
////        bowl.add(new Player("D chahar", 8.5, ft, 54.0, 45.0, 2.0, 54.0, 6.0, -2.0, 40.0, 6.0, 74.0));
////        bowl.add(new Player("P chawla", 8.5, ft, 31.0, 23.0, 54.0, 29.0, 25.0, 0.0, 0.0, 4.0, 0.0));
////        bowl.add(new Player("K sharma", 8.0, ft, 0.0, 0.0, 0.0, 0.0, 0.0, 54.0, 4.0, 52.0, -2.0));
////
////    }


}