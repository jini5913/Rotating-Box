package org.noid;

import javax.swing.*;


public class Main {
    //frame setting
    public static final int frameWidth = 1200;
    public static final int frameHeight = 1200;
    public static SquarePanel squarePanel = new SquarePanel();


    public static int k = 0;
    public static int l = 0;
    public static Vector3 rotate = new Vector3(0, 0, 0);

    public static int n = 0;
    public static int m = 0;


    public static void main(String[] args) {

        Box box = new Box();



        JFrame frame = new JFrame("Square Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.add(squarePanel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();

        frame.addKeyListener(new KeyListener());
        frame.addMouseListener(new MouseListener());

        RayCaster rayCaster = new RayCaster();

        while (true){

            box.RotateBox(0.01f * l + 0.1f * rotate.x, 0.01f * l + 0.1f * rotate.y, 0.01f * l + 0.1f * rotate.z);
//            box.RotateYAxis(0.1f * k);
//            box.RotateZAxis(0.1f * k);
//            box.RotateXAxis(0.1f * k);
            RayCaster.whiteLight.RotateLight(0f, 0, 0.05f * k);

            box.calculateSurface();


            rayCaster.cast();

            squarePanel.repaint();

            k += n;
            l += m;
        }



    }


}