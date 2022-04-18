package GameIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Actions implements KeyListener {

    @Override
    public void keyTyped( KeyEvent e ) {
        System.out.println("rotate");

    }

    @Override
    public void keyPressed( KeyEvent e ) {
        System.out.println("rotate");

    }

    @Override
    public void keyReleased( KeyEvent e ) {
        System.out.println("rotate");

    }
}
