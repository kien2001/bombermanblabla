package com.t3h.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vaio on 11/08/2016.
 */
public class Bomb {
    public static final int SIZE = 45;
    public final Image[] IMAGE = {
            new ImageIcon(getClass().getResource("/images/bomb1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomb2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomb3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomb4.png")).getImage()
    };
    private int x, y;
    private boolean checkSetTime;
    private boolean checkBang;
    private int animationTime = 30;
    private int currentImage;
    private int duration = 500, time = 0;
    private int strength;

    public Bomb(int x, int y, int strength) {
        checkBang = true;
        this.x = x;
        this.y = y;
        this.strength = strength;
    }

    public void draw(Graphics2D g2d, int t) {
        // ve bom animation
        if (t % animationTime == 0) {
            if (currentImage < 3) {
                currentImage++;
            } else {
                currentImage = 0;
            }
        }
        g2d.drawImage(IMAGE[currentImage], x, y, 45, 45, null);

    }

    public boolean bangTimeArrive(int t) {
        // kiem tra xem den thoi gian no bom hay chua
        if (!checkSetTime) {
            time = t;
            checkSetTime = true;
        }
        if (t - time < duration) {
            return false;
        }
        return true;
    }

    public WaterBomb bang(int t, BitMap bitMap) {
        // cho bom no
        WaterBomb waterBomb = new WaterBomb(x, y);
        waterBomb.setStartTime(t);
        waterBomb.checkDraw(bitMap);
        waterBomb.setDamage(strength);
        return waterBomb;
    }

    public boolean bangByBomb(ArrayList<WaterBomb> waterBombs) {
        // va cham giua bom va nuoc bom
        for (int i = 0; i < waterBombs.size(); i++) {
            WaterBomb waterBomb = waterBombs.get(i);
            Rectangle rectangleUp = getRect().intersection(waterBomb.getRectUp());
            Rectangle rectangleDown = getRect().intersection(waterBomb.getRectDown());
            Rectangle rectangleLeft = getRect().intersection(waterBomb.getRectLeft());
            Rectangle rectangleRight = getRect().intersection(waterBomb.getRectRight());
            if (!rectangleUp.isEmpty() || !rectangleDown.isEmpty() || !rectangleLeft.isEmpty() || !rectangleRight.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, SIZE, SIZE);
        return rectangle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
