package com.t3h.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vaio on 09/08/2016.
 */
public class MyFrame extends JFrame {
    public static final int WIDTH = 995;
    public static final int HEIGHT = 794;

    public MyFrame() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        setTitle("Bomberman");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new PanelManager());
    }
}
