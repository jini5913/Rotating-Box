package org.noid;

public class Box {

    public static final Vector3[] vertex = new Vector3[]{
            new Vector3(-10, -10, -10),
            new Vector3(10, -10, -10),
            new Vector3(-10, 10, -10),
            new Vector3(10, 10, -10),
            new Vector3(-10, -10, 10),
            new Vector3(10, -10, 10),
            new Vector3(-10, 10, 10),
            new Vector3(10, 10, 10)
    };


    public static Vector3[] nowVer = new Vector3[]{
            new Vector3(-10, -10, -10),
            new Vector3(10, -10, -10),
            new Vector3(-10, 10, -10),
            new Vector3(10, 10, -10),
            new Vector3(-10, -10, 10),
            new Vector3(10, -10, 10),
            new Vector3(-10, 10, 10),
            new Vector3(10, 10, 10)
    };

    public static double[][] surfaceCoef = new double[6][4];


    public Box(){

        calculateSurface();
    }

    public void RotateBox(float rotX, float rotY, float rotZ){
        for (int i = 0; i < 8; i++){
            nowVer[i].x = (float) (vertex[i].x * Math.cos(rotY) * Math.cos(rotZ) + vertex[i].y * (Math.sin(rotX) * Math.sin(rotY) * Math.cos(rotZ) - Math.cos(rotX) * Math.sin(rotZ)) + vertex[i].z * (Math.cos(rotX) * Math.sin(rotY) * Math.cos(rotZ) + Math.sin(rotX) * Math.sin(rotZ)));
            nowVer[i].y = (float) (vertex[i].x * Math.cos(rotY) * Math.sin(rotZ) + vertex[i].y * (Math.sin(rotX) * Math.sin(rotY) * Math.sin(rotZ) + Math.cos(rotX) * Math.cos(rotZ)) + vertex[i].z * (Math.cos(rotX) * Math.sin(rotY) * Math.sin(rotZ) - Math.sin(rotX) * Math.cos(rotZ)));
            nowVer[i].z = (float) (vertex[i].x * Math.sin(rotY) * -1 + vertex[i].y * Math.sin(rotX) * Math.cos(rotY) + vertex[i].z * Math.cos(rotX) * Math.cos(rotY));
        }
    }


    public void RotateXAxis(float rotX){
        for (int i = 0; i < 8; i++) {
            nowVer[i].y = (float) (vertex[i].y * Math.cos(rotX) - vertex[i].z * Math.sin(rotX));
            nowVer[i].z = (float) (vertex[i].y * Math.sin(rotX) + vertex[i].z * Math.cos(rotX));

        }
    }

    public void RotateYAxis(float rotY){
        for (int i = 0; i < 8; i++) {
            nowVer[i].x = (float) (vertex[i].x * Math.cos(rotY) + vertex[i].z * Math.sin(rotY));
            nowVer[i].z = (float) (vertex[i].x * -Math.sin(rotY) + vertex[i].z * Math.cos(rotY));

        }
    }

    public void RotateZAxis(float rotZ){
        for (int i = 0; i < 8; i++) {
            nowVer[i].x = (float) (vertex[i].x * Math.cos(rotZ) - vertex[i].y * Math.sin(rotZ));
            nowVer[i].y = (float) (vertex[i].x * Math.sin(rotZ) + vertex[i].y * Math.cos(rotZ));

        }
    }



    public void calculateSurface(){

        Vector3 vec1;
        Vector3 vec2;
        Vector3 vec3;

        for (int i = 0; i < 6; i++){
            vec1 = nowVer[RayCaster.panelSet[i][0]];
            vec2 = nowVer[RayCaster.panelSet[i][1]];
            vec3 = nowVer[RayCaster.panelSet[i][2]];
            surfaceCoef[i][0] = vec1.y * (vec2.z - vec3.z) + vec2.y * (vec3.z - vec1.z) + vec3.y * (vec1.z - vec2.z);
            surfaceCoef[i][1] = vec1.z * (vec2.x - vec3.x) + vec2.z * (vec3.x - vec1.x) + vec3.z * (vec1.x - vec2.x);
            surfaceCoef[i][2] = vec1.x * (vec2.y - vec3.y) + vec2.x * (vec3.y - vec1.y) + vec3.x * (vec1.y - vec2.y);
            surfaceCoef[i][3] = vec1.x * (vec2.y * vec3.z - vec3.y * vec2.z) + vec2.x * (vec3.y * vec1.z - vec1.y * vec3.z) + vec3.x * (vec1.y * vec2.z - vec2.y * vec1.z );

        }

        //System.out.println(surfaceCoef[1][0] + " " + surfaceCoef[1][1] + " " + surfaceCoef[1][2]);
    }

    public static Vector3 findInterSection(Vector3 p1,  Vector3 p2, int surface) {
        Vector3 interSection;
        Vector3 normalVec = new Vector3((float) surfaceCoef[surface][0], (float) surfaceCoef[surface][1], (float) surfaceCoef[surface][2]);
        boolean isAllSame = true;
        float[] value = new float[4];

        //
        double denominator = surfaceCoef[surface][0] * (p1.x - p2.x) + surfaceCoef[surface][1] * (p1.y - p2.y) + surfaceCoef[surface][2] * (p1.z - p2.z);
        if (denominator == 0) return null;

        double numerator = surfaceCoef[surface][0] * p1.x +surfaceCoef[surface][1] * p1.y + surfaceCoef[surface][2] * p1.z - surfaceCoef[surface][3];

        double u = numerator / denominator;

        if(u < 0){
            return null;
        }

        interSection = Vector3.sum(Vector3.mul(Vector3.sub(p2, p1), (float) u), p1);

        //find if its in square

////////////////////////////////////////////////////


        for (int i = 0; i < 4; i++){
            Vector3 pa1 = Vector3.sub(nowVer[RayCaster.panelSet[surface][i]], interSection);
            Vector3 pa2 = Vector3.sub(nowVer[RayCaster.panelSet[surface][(i + 1) % 4]], interSection);

            value[i] = Vector3.inner(Vector3.cross(pa1, pa2), normalVec);

        }
        for (int j = 0; j < 4; j++){
            if (value[j] * value[(j + 1) % 4] <= 0){
                isAllSame = false;
                break;
            }

        }


////////////////////////////////////////////////////


//        for (int i = 0; i < 4; i++){
//            Vector3 pa1 = Vector3.sub(nowVer[RayCaster.panelSet[surface][i]], interSection);
//            Vector3 pa2 = Vector3.sub(nowVer[RayCaster.panelSet[surface][(i + 1) % 4]], interSection);
//
//            value[i] = Vector3.inner(pa1, pa2) / (Vector3.mag(pa1, Vector3.zero()) * Vector3.mag(pa2, Vector3.zero()));
//        }
//
//        float sum = 0;
//        for (int j = 0; j < 4; j++){
//            sum += (Math.acos(value[j]));
//
//        }
//
//        if ( Math.round(sum * 100) / 100f <= 6.27f){
//            isAllSame = false;
//        }


////////////////////////////////////////////////////
        if (!isAllSame){
            return null;
        }else {
            return interSection;
        }



    }
}
