package org.noid;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    public static final int pixelSize = 256;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        int width = Main.frameWidth / pixelSize;
        int height = Main.frameHeight / pixelSize;

        for (int i = 0; i < pixelSize; i++) {
            for (int j = 0; j < pixelSize; j++) {

                int x = j * width;
                int y = i * height;

                g2d.setColor(RayCaster.colorMap[i][j]);
                g2d.fillRect(x, y, width, height);

            }
        }
    }

}