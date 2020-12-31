package prediction;

public class TeamArrangement {
    private final int w;
    private final int ba;
    private final int a;
    private final int bo;

    public int getW() {
        return w;
    }

    public int getBa() {
        return ba;
    }

    public int getA() {
        return a;
    }

    public int getBo() {
        return bo;
    }

    public TeamArrangement(int w, int ba, int a, int bo) {
        this.w = w;
        this.ba = ba;
        this.a = a;
        this.bo = bo;
    }
}
