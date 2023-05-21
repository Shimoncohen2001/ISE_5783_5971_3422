package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    public List<LightSource> lights=new LinkedList<>();

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

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
