package org.noid;

public class Vector3 {
    float x, y, z;

    public Vector3(float vX, float vY, float vZ){
        x = vX;
        y = vY;
        z = vZ;
    }


    public static float mag(Vector3 a, Vector3 b){
        return (float)(Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2)));
    }


    public static Vector3 zero () {return new Vector3(0, 0, 0);}

    public static Vector3 sub (Vector3 a, Vector3 b){ return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);}

    public static Vector3 sub (Vector3 a, float i){
        return new Vector3(a.x - i, a.y - i, a.z - i);
    }

    public static Vector3 sum (Vector3 a, Vector3 b){ return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);}

    public static Vector3 sum (Vector3 a, float i){
        return new Vector3(a.x + i, a.y + i, a.z + i);
    }

    public static Vector3 div (Vector3 a, Vector3 b){
     return new Vector3(a.x / b.x, a.y / b.y, a.z / b.z);
    }

    public static Vector3 div (Vector3 a, float i){
        return new Vector3(a.x / i, a.y / i, a.z / i);
    }

    public static Vector3 mul (Vector3 a, Vector3 b){
        return new Vector3(a.x * b.x, a.y * b.y , a.z * b.z);
    }

    public static Vector3 mul (Vector3 a, float i){
        return new Vector3(a.x * i, a.y * i, a.z * i);
    }

    public static Vector3 cross (Vector3 a, Vector3 b) { return new Vector3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);}

    public static float inner (Vector3 a, Vector3 b) { return (a.x * b.x + a.y * b.y + a.z * b.z);}
}
