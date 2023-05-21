package primitives;

public class Material {
    public Double3 kD=Double3.ZERO;
    public Double3 ks=Double3.ZERO;
    public int nShininess=0;

    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }
    public Material setkD(double kD) {
        this.kD  =new Double3(kD);
        return this;
    }

    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
