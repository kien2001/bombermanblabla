package com.t3h.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vaio on 09/08/2016.
 */
public class PointImage {
    public static final int BOX1 = 0;
    public static final int SHADOW_BOX1 = 1;
    public static final int STONE = 2;
    public static final int BOX2 = 3;
    public static final int SHADOW_BOX2 = 4;
    public static final int ROAD = 5;
    public static final int MAGICAL = 6;
    public static final int BOOM = 7;
    public static final int HASTE = 8;
    private int x, y;
    private int bitValue;
    private final Image[] IMAGE = {
            new ImageIcon(getClass().getResource("/images/box1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/shawdow1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/stone.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/box2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/shawdow2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/magical.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/boom.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/haste.png")).getImage()
    };

    public PointImage(int x, int y, int bitValue) {
        this.x = x;
        this.y = y;
        this.bitValue = bitValue;
    }

    public void drawPoint(Graphics2D g2d) {
        // ve hinh anh tuong ung voi bitmap
        if (bitValue == BOX1) {
            g2d.drawImage(IMAGE[BOX1], x, y, null);
        }
        if (bitValue == STONE) {
            g2d.drawImage(IMAGE[STONE], x, y, null);
        }
        if (bitValue == BOX2) {
            g2d.drawImage(IMAGE[BOX2], x, y, null);
        }
        if (bitValue == BOOM) {
            g2d.drawImage(IMAGE[BOOM - 1], x + 5, y, null);
        }
        if (bitValue == MAGICAL) {
            g2d.drawImage(IMAGE[MAGICAL - 1], x + 5, y, null);
        }
        if (bitValue == HASTE) {
            g2d.drawImage(IMAGE[HASTE - 1], x + 5, y, null);
        }

    }

    public void drawShadow(Graphics2D g2d) {
        // ve bong hinh anh tuong ung vat the
        if (bitValue == BOX1) {
            g2d.drawImage(IMAGE[SHADOW_BOX1], x, y - IMAGE[SHADOW_BOX1].getHeight(null), null);
        }
        if (bitValue == BOX2) {
            g2d.drawImage(IMAGE[SHADOW_BOX2], x, y - IMAGE[SHADOW_BOX2].getHeight(null), null);
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle;
        if (bitValue == 2) {
            rectangle = new Rectangle(x, y + 10, IMAGE[STONE].getWidth(null), IMAGE[STONE].getHeight(null) - 40);
        } else {
            rectangle = new Rectangle(x, y, IMAGE[0].getWidth(null) - 5, IMAGE[0].getHeight(null) - 5);
        }
        return rectangle;
    }

    public int getBitValue() {
        return bitValue;
    }

    public void setBitValue(int bitValue) {
        this.bitValue = bitValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
