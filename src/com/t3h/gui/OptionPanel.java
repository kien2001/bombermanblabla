package com.t3h.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by vaio on 16/08/2016.
 */
public class OptionPanel extends JPanel implements KeyListener {
    private final Image IMAGE = new ImageIcon(getClass().getResource("/images/background_option.png")).getImage(); // anh nen
    private CardLayout cardLayout;

    public OptionPanel() {
        setLayout(null);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(IMAGE, 0, 0, MyFrame.WIDTH, MyFrame.HEIGHT + 30, null);
    }

    public void setCardLayout(CardLayout cardLayout) {
        // set cardlayout cua component cha
        this.cardLayout = cardLayout;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // nhan phim de tro lai menu
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            cardLayout.show(this.getParent(), "StartMenuPanel");
            this.requestFocusInWindow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
