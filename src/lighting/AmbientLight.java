package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    private Color Intensity;
    public static final AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);
    public AmbientLight(Color Ia, Double3 Ka) {
        Intensity=Ia.scale(Ka);
    }
    public AmbientLight(double Ka){
    }

    public Color getIntensity() {
        return Intensity;
    }

}
