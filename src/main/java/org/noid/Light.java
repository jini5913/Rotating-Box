package org.noid;

public class Light {
    Vector3 position;
    Vector3 newPosition;
    public Light(Vector3 position){
        this.position = new Vector3(position.x, position.y, position.z);
        this.newPosition = new Vector3(position.x, position.y, position.z);
    }
    public double FindIntensity(Vector3 intersection, int intense){
        float distance = Vector3.mag(intersection, newPosition);
        return intense/(distance * distance);
    }

    public void RotateLight(float rotX, float rotY, float rotZ){
        newPosition.x = (float) (position.x * Math.cos(rotY) * Math.cos(rotZ) + position.y * (Math.sin(rotX) * Math.sin(rotY) * Math.cos(rotZ) - Math.cos(rotX) * Math.sin(rotZ)) + position.z * (Math.cos(rotX) * Math.sin(rotY) * Math.cos(rotZ) + Math.sin(rotX) * Math.sin(rotZ)));
        newPosition.y = (float) (position.x * Math.cos(rotY) * Math.sin(rotZ) + position.y * (Math.sin(rotX) * Math.sin(rotY) * Math.sin(rotZ) + Math.cos(rotX) * Math.cos(rotZ)) + position.z * (Math.cos(rotX) * Math.sin(rotY) * Math.sin(rotZ) - Math.sin(rotX) * Math.cos(rotZ)));
        newPosition.z = (float) (position.x * Math.sin(rotY) * -1 + position.y * Math.sin(rotX) * Math.cos(rotY) + position.z * Math.cos(rotX) * Math.cos(rotY));
    }


    public static float CalculateReflect(int surface, Light light, Vector3 intersection){


        for (int i = 0; i < 6; i++){
            if (i != surface){
                if (Box.findInterSection(intersection, light.newPosition, i) != null){
                    return 0;
                }
            }
        }

        Vector3 p1 = new Vector3((float)  Box.surfaceCoef[surface][0], (float) Box.surfaceCoef[surface][1], (float) Box.surfaceCoef[surface][2]);
        Vector3 p2 = light.newPosition;




        float numer = Math.abs(Vector3.inner(p1, p2));

        float denom = Vector3.mag(p1, Vector3.zero()) * Vector3.mag(p2, Vector3.zero());

        return Math.max(numer/denom, 0);
    }
}
