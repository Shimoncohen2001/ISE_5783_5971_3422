package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    private Point p0;
    private Vector vto;
    private Vector vup;
    private Vector vright;

    private double width;
    private double height;
    private double distance;


    public Camera(Point p0, Vector vto, Vector vup) {
        this.p0 = p0;
        this.vto = vto.normalize();
        this.vup = vup.normalize();
        if (vto.dotProduct(vup) == 0) {
            this.vright = vto.crossProduct(vup).normalize();
        } else {
            throw new IllegalArgumentException("the two unit vectors vto and vup are not orthogonal");
        }
    }
    public Camera setVPSize(double width, double height)
    {
        this.width=width;
        this.height=height;
        return this;
    }
    public Camera setVPDistance(double distance)
    {
        this.distance=distance;
        return this;
    }
    public Ray constructRay(int nX,int nY,int j,int i){
        Point pc= p0.add(vto.scale(distance));
        double Ry=height/nY;
        double Rx=width/nX;
        double yi=-(i-(nY-1)/2)*Ry;
        double xj=(j-(nX-1)/2)*Rx;
        Point pi_j=pc;
        if (xj!=0){
            pi_j=pi_j.add(vright.scale(xj));
        }if (yi!=0){
            pi_j=pi_j.add(vup.scale(yi));
        }
        Vector vi_j=pi_j.subtract(p0);
        Ray ray= new Ray(p0,vi_j);
        return ray;
    }

}
