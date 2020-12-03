package com.t3h.model;

import com.t3h.gui.MyFrame;
import com.t3h.gui.PlayedPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Created by vaio on 09/08/2016.
 */
public class InfoGaming {
    private final Image IMAGE_GROUND = new ImageIcon(getClass().getResource("/images/background_Info.jpg")).getImage();
    private final Image[] IMAGE = {
            new ImageIcon(getClass().getResource("/images/life.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/x.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/0.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/6.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/7.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/8.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/9.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/boom.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/magical.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/haste.png")).getImage()
    };
    public static final int LIFE = 0;
    public static final int X = 1;
    public static final int ZERO = 2;
    public static final int ONE = 3;
    public static final int TWO = 4;
    public static final int THREE = 5;
    public static final int FOUR = 6;
    public static final int FIVE = 7;
    public static final int SIX = 8;
    public static final int SEVEN = 9;
    public static final int EIGHT = 10;
    public static final int NINE = 11;
    public static final int BOOM = 12;
    public static final int MAGICAL = 13;
    public static final int HASTE = 14;
    private int life = 3;
    private int boom = 1;
    private int magical = 1;
    private int haste = 1;

    public InfoGaming() {

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(IMAGE_GROUND, PlayedPanel.WIDTH, 0, MyFrame.WIDTH - PlayedPanel.WIDTH, PlayedPanel.HEIGHT, null);
        int xLife = 800, yLife = 150;
        g2d.drawImage(IMAGE[LIFE], xLife, yLife, null);
        int xX = xLife + 70, yX = yLife + 10;
        g2d.drawImage(IMAGE[X], xX, yX, null);
        int xNumber = xX + 70, yNumber = yX;
        drawNumber(g2d, life, xNumber, yNumber);


        int xBoom = xLife, yBoom = yLife + 50;
        g2d.drawImage(IMAGE[BOOM], xBoom, yBoom, null);
        int xMagical = xBoom, yMagical = yBoom + 50;
        g2d.drawImage(IMAGE[MAGICAL], xMagical, yMagical, null);
        int xHaste = xMagical, yHaste = yMagical + 50;
        g2d.drawImage(IMAGE[HASTE], xHaste, yHaste, null);

        int xXBoom = xLife + 70, yXBoom = yLife + 70;
        g2d.drawImage(IMAGE[X], xXBoom, yXBoom, null);
        int xXMagical = xBoom + 70, yXMagical = yXBoom + 50;
        g2d.drawImage(IMAGE[X], xXMagical, yXMagical, null);
        int xXHaste = xMagical + 70, yXHaste = yMagical + 70;
        g2d.drawImage(IMAGE[X], xXHaste, yXHaste, null);

        drawNumber(g2d, boom, xXBoom + 70, yXBoom);
        drawNumber(g2d, magical, xXMagical + 70, yXMagical);
        drawNumber(g2d, haste, xXHaste + 70, yXHaste);

    }

    public void drawNumber(Graphics2D g2d, int number, int x, int y) {
        switch (number) {
            case 0:
                g2d.drawImage(IMAGE[ZERO], x, y, null);
                break;
            case 1:
                g2d.drawImage(IMAGE[ONE], x, y, null);
                break;
            case 2:
                g2d.drawImage(IMAGE[TWO], x, y, null);
                break;
            case 3:
                g2d.drawImage(IMAGE[THREE], x, y, null);
                break;
            case 4:
                g2d.drawImage(IMAGE[FOUR], x, y, null);
                break;
            case 5:
                g2d.drawImage(IMAGE[FIVE], x, y, null);
                break;
            case 6:
                g2d.drawImage(IMAGE[SIX], x, y, null);
                break;
            case 7:
                g2d.drawImage(IMAGE[SEVEN], x, y, null);
                break;
            case 8:
                g2d.drawImage(IMAGE[EIGHT], x, y, null);
                break;
            case 9:
                g2d.drawImage(IMAGE[NINE], x, y, null);
                break;

        }
    }

    public void update(int life, int magical, int boom, int haste) {
        this.life = life;
        this.boom = boom;
        this.magical = magical;
        this.haste = haste;
    }
}
