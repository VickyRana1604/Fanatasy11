package prediction;

public class TeamArrangement {
    private final int w;
    private final int ba;
    private final int a;
    private final int bo;

    public int getW() {
        return 2 * w;
    }

    public int getBa() {
        return 2 * ba;
    }

    public int getA() {
        return 2 * a;
    }

    public int getBo() {
        return 2 * bo;
    }

    public TeamArrangement(int w, int ba, int a, int bo) {
        this.w = w;
        this.ba = ba;
        this.a = a;
        this.bo = bo;
    }
}
