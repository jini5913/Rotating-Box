package org.noid;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e){
//            System.out.println(e.getKeyChar());
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A)
        {
            Main.rotate.y -= 1;
        }

        if (key == KeyEvent.VK_D)
        {
            Main.rotate.y += 1;
        }

        if (key == KeyEvent.VK_S)
        {
            Main.rotate.x -= 1;
        }

        if (key == KeyEvent.VK_W)
        {
            Main.rotate.x += 1;
        }

        if (key == KeyEvent.VK_E)
        {
            Main.rotate.z -= 1;
        }

        if (key == KeyEvent.VK_Q)
        {
            Main.rotate.z += 1;
        }

        if (key == KeyEvent.VK_SPACE){
            RayCaster.rayStartPosition = new Vector3(0, 0, -30);
            Main.rotate = Vector3.zero();
            Main.k = 0;
            Main.n = 0;
            Main.m = 0;
            Main.l = 0;
        }

        if (key == KeyEvent.VK_UP){
            RayCaster.rayStartPosition.z += 1;
        }

        if (key == KeyEvent.VK_DOWN){
            RayCaster.rayStartPosition.z -= 1;
        }

        if (key == KeyEvent.VK_LEFT){
            Main.n = 1;
        }

        if (key == KeyEvent.VK_RIGHT){
            Main.n = -1;
        }

        if (key == KeyEvent.VK_O){
            Main.m = 1;
        }

        if (key == KeyEvent.VK_P){
            Main.m = 0;
        }

        if (key == KeyEvent.VK_I){
            Main.m = -1;
        }

    }
}
