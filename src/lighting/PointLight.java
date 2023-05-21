package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    protected PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    private Point position;
    private double kC=1;
    private double kL=0;
    private double kQ=0;

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Vector getL(Point p) {
        try{
            return p.subtract(this.position).normalize();
        }catch (Exception e) {
            return null;
        }
    }
    @Override
    public Color getIntensity(Point p) {
        double factor=kC;
        double distance;
        try{
            distance=position.distance(p);
            factor+=kL*distance+kQ*distance*distance;
        }catch (Exception e) {
            return null;
        }
        return getIntensity().scale(1/factor);
    }
}
