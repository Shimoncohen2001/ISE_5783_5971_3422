package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

public class Camera {
    private Point p0;
    private Vector vto;
    private Vector vup;
    private Vector vright;

    public void setHeight(double height) {
        this.height = height;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setP0(Point p0) {
        this.p0 = p0;
    }

    public void setVto(Vector vto) {
        this.vto = vto;
    }

    public void setVup(Vector vup) {
        this.vup = vup;
    }

    public void setVright(Vector vright) {
        this.vright = vright;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    private double width;
    private double height;
    private double distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    public  Camera(Point p0, Vector vto, Vector vup) {
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
        double yi=-(i-(double)(nY-1)/2)*Ry;
        double xj=(j-(double)(nX-1)/2)*Rx;
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
    public void renderImage(){
        if (p0==null || vup==null|| vto==null
                || vright==null|| width==0|| height==0
                || distance==0 || imageWriter==null
                || rayTracerBase==null){
            throw new MissingResourceException("missing ressource",getClass().getName(),"");
        }
        Color color;
        for(int i=0;i<imageWriter.getNx();i++)
        {
            for(int j=0;j<imageWriter.getNy();j++)
            {
                color=castRay(j,i,imageWriter.getNx(),imageWriter.getNy());
                imageWriter.writePixel(j,i,color);
            }
        }
    }
    public void printGrid(int interval, Color color){
        if(imageWriter==null){
            throw new MissingResourceException("imageWriter is null",getClass().getName(),"");
        }


        int row=0;
        int col=0;

        for(; row < 10; row++) {
            for(int j = 0; j < 1000; j++) {
                imageWriter.writePixel(row*100, j, color);
            }
        }

        for(; col < 10; col++) {
            for(int j = 0; j < 1000; j++) {
                imageWriter.writePixel(j, col*100, color);
            }
        }

        imageWriter.writeToImage();
    }
    public void writeToImage(){
        if(imageWriter==null){
            throw new MissingResourceException("imageWriter is null",getClass().getName(),"");
        }
        imageWriter.writeToImage();
    }
    public Color castRay(int j,int i,int nX,int nY){
        Ray ray=constructRay(nX,nY,j,i);
        Color color=rayTracerBase.traceRay(ray);
        return color;
    }

}
