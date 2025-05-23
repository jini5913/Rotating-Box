package org.noid;


import java.awt.*;


public class RayCaster {
    public static final int[][] panelSet = new int[][]{
            {0, 1, 3, 2},//front
            {0, 2, 6, 4},//left
            {2, 3, 7, 6},//top
            {4, 5, 7, 6},//back
            {1, 3, 7, 5},//right
            {0, 1, 5, 4}//bottom
    };

    public static Vector3 rayStartPosition = new Vector3(0, 0, -30);

    public static Color[][] colorMap = new Color[SquarePanel.pixelSize][SquarePanel.pixelSize];

    public static Light whiteLight = new Light(new Vector3(0, 20, -30));
    public static Light redLight = new Light(new Vector3(0, 30, -15));
    public static Light blueLight = new Light(new Vector3(-10, -25, -15));
    public static Light greenLight = new Light(new Vector3(10, -25, -15));

    public RayCaster() {


    }

    public void cast() {
        for (int i = 0; i < SquarePanel.pixelSize; i++) {
            for (int j = 0; j < SquarePanel.pixelSize; j++) {
                float[] interSectionDistance = new float[6];
                Vector3[] intersetion = new Vector3[6];
                float smallest = 10000;
                int smallestIndex = -1;
                int wht = 0;
                int red = 0;
                int blue = 0;
                int green = 0;

                Vector3 displayPixel = new Vector3(30 * (2.0f * j / (SquarePanel.pixelSize - 1) - 1), -30 * (2.0f * i / (SquarePanel.pixelSize - 1) - 1), rayStartPosition.z + 10);

                for(int surface = 0; surface < 6; surface++){
                    Vector3 tempVec;

                    if (Box.findInterSection(RayCaster.rayStartPosition ,displayPixel, surface) == null) {
                        tempVec = RayCaster.rayStartPosition;

                    }else {
                        tempVec = Box.findInterSection(RayCaster.rayStartPosition, displayPixel, surface);
                    }

                    interSectionDistance[surface] = Vector3.mag(tempVec, RayCaster.rayStartPosition);
                    intersetion[surface] = tempVec;
                }



                //find nearest surface
                for(int k = 0; k < 6; k++){
                    if (interSectionDistance[k] > 0 && smallest > interSectionDistance[k]){
                        smallest = interSectionDistance[k];
                        smallestIndex = k;
                    }
                }

                //find light
                if (smallestIndex != -1){
                    wht = (int)whiteLight.FindIntensity(Box.findInterSection(RayCaster.rayStartPosition, displayPixel, smallestIndex), 100000);
                    red = (int)redLight.FindIntensity(Box.findInterSection(RayCaster.rayStartPosition,displayPixel, smallestIndex), 80000);
                    blue = (int)blueLight.FindIntensity(Box.findInterSection(RayCaster.rayStartPosition, displayPixel, smallestIndex), 80000);
                    green = (int)greenLight.FindIntensity(Box.findInterSection(RayCaster.rayStartPosition,  displayPixel, smallestIndex), 80000);


                    wht *= Math.abs(Light.CalculateReflect(smallestIndex, whiteLight, intersetion[smallestIndex]));
                    red *= Math.abs(Light.CalculateReflect(smallestIndex, redLight, intersetion[smallestIndex]));
                    blue *= Math.abs(Light.CalculateReflect(smallestIndex, blueLight, intersetion[smallestIndex]));
                    green *= Math.abs(Light.CalculateReflect(smallestIndex, greenLight, intersetion[smallestIndex]));

                }



                //all color

                Color color = new Color(Math.min((wht + red), 255), Math.min((wht + green), 255), Math.min((wht + blue), 255));

                //white only

                //Color color = new Color(Math.min((wht), 255), Math.min((wht), 255), Math.min((wht), 255));


                //set Color
                if(smallestIndex == -1){
                    colorMap[i][j] = new Color(0, 0, 0);
//                } else if(smallestIndex == 0){
//                    colorMap[i][j] = color;
//                } else if(smallestIndex == 1){
//                    colorMap[i][j] = color;
//                } else if(smallestIndex == 2){
//                    colorMap[i][j] = color;
//                } else if(smallestIndex == 3){
//                    colorMap[i][j] = color;
//                } else if(smallestIndex == 4){
//                    colorMap[i][j] = color;
                } else{
                    colorMap[i][j] = color;
                }
            }
        }
    }
}
