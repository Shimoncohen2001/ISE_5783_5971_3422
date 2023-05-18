package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;

public class Scene {
   public String name;
   public Color background= new Color(0,0,0);
   public AmbientLight ambientLight= AmbientLight.NONE;
   public Geometries geometries=new Geometries();

    public  Scene(String name) {
        this.name = name;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

}
