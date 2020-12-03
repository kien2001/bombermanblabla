package com.t3h.gui;

import sounds.SoundManager;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

/**
 * Created by vaio on 09/08/2016.
 */
public class StartMenuPanel extends JPanel {
    private final Image IMAGE = new ImageIcon(getClass().getResource("/images/background_Menu.png")).getImage();
    private Clip clip;

    public StartMenuPanel() {
        setLayout(null);
        clip = SoundManager.getSound(getClass().getResource("/sounds/BG.wav"));
        clip.loop(100);
    }

    public void playSound() {
        if (!clip.isRunning()) {
            clip.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(IMAGE, 0, 0, MyFrame.WIDTH, MyFrame.HEIGHT, null);
    }
}
