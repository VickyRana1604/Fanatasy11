package Data;

import Util.DateUtil;

import java.util.Calendar;

public class PlayerStats {
    public String vsTeam;
    public String date;
    public String sel;
    public String point;
    public String credit;
    public String predictedPoint;

    public PlayerStats(String vsTeam, String date, String sel, String point, String credit) {
        this.vsTeam = vsTeam;
        this.date = date;
        this.sel = sel;
        this.point = point;
        this.credit = credit;
    }

    public double getSel() {
        return Double.parseDouble(sel.replaceAll("%", ""));
    }

    public double getCredit() {
        return Double.parseDouble(credit);
    }

    public double getPoint() {
        return Double.parseDouble(point);
    }
}
