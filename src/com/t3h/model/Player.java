package com.t3h.model;

import com.t3h.gui.PlayedPanel;
import sounds.SoundManager;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vaio on 09/08/2016.
 */
public class Player {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SIZE = 45;
    private int x, y; // Player
    private int orient; // Player
    private int timeMove = 5; //move()
    private int speed = 5; // move()
    private int animationTimeEffect = 10; //  drawDinamic()
    private int animationTimeMove = 30; //  drawDinamic()
    private int timePlant = 30, time; // plantBomb()
    private int xNext; // move()
    private int yNext; // move()
    private int maxBomb = 2;// pickItem()
    private int maxSpeed = 1;// pickItem()
    private int maxStrength = 1;// pickItem()
    private int maxLife = 3; // current life
    private Clip clipNewBomb;
    private Clip clipPickItem;
    private int currentImageEffect = 0;
    private int currentImageMove = 0;// drawDinamic()
    private int currentImageDie = 0;//drawDie()
    private boolean checkSetXYDie;// drawDie()
    private int x_tmp, y_tmp;// drawDie()
    private final Image[] IMAGE = {
            new ImageIcon(getClass().getResource("/images/bomber_up1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_down1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_right1.png")).getImage(),
    };
    private final Image[] IMAGE_EFFECT = {
            new ImageIcon(getClass().getResource("/images/effect1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect6.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect7.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/effect8.png")).getImage(),

    };
    private final Image[] IMAGE_UP = {
            new ImageIcon(getClass().getResource("/images/bomber_up1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_up2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_up3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_up4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_up5.png")).getImage()
    };
    private final Image[] IMAGE_DOWN = {
            new ImageIcon(getClass().getResource("/images/bomber_down1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_down2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_down3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_down4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_down5.png")).getImage()

    };
    private final Image[] IMAGE_LEFT = {
            new ImageIcon(getClass().getResource("/images/bomber_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_left3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_left4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_left5.png")).getImage()

    };
    private final Image[] IMAGE_RIGHT = {
            new ImageIcon(getClass().getResource("/images/bomber_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_right2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_right3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_right4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_right5.png")).getImage()
    };
    private final Image[] IMAGE_DIE = {
            new ImageIcon(getClass().getResource("/images/bomber_die1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die6.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die7.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die8.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die9.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die10.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bomber_die11.png")).getImage(),
    };

    public Player(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    public void drawStatic(Graphics2D g2d) {
        // ve animation khi di chuyen
        g2d.drawImage(IMAGE[orient], x, y, IMAGE[0].getWidth(null), IMAGE[0].getHeight(null), null);
    }

    public void drawDinamic(Graphics2D g2d, int t) {
        // ve player khong co animation
        if (t % animationTimeMove == 0) {
            if (currentImageMove < 4) {
                currentImageMove++;
            } else {
                currentImageMove = 0;
            }
        }
        switch (orient) {
            case UP:
                int widthUp = IMAGE_UP[currentImageMove].getWidth(null);
                int heightUp = IMAGE_UP[currentImageMove].getHeight(null);
                g2d.drawImage(IMAGE_UP[currentImageMove], x, y, widthUp, heightUp, null);
                break;
            case DOWN:
                int widthDown = IMAGE_DOWN[currentImageMove].getWidth(null);
                int heightDown = IMAGE_DOWN[currentImageMove].getHeight(null);
                g2d.drawImage(IMAGE_DOWN[currentImageMove], x, y, widthDown, heightDown, null);
                break;
            case LEFT:
                int widthLeft = IMAGE_LEFT[currentImageMove].getWidth(null);
                int heightLeft = IMAGE_LEFT[currentImageMove].getHeight(null);
                g2d.drawImage(IMAGE_LEFT[currentImageMove], x, y, widthLeft, heightLeft, null);
                break;
            case RIGHT:
                int widthRight = IMAGE_RIGHT[currentImageMove].getWidth(null);
                int heightRight = IMAGE_RIGHT[currentImageMove].getHeight(null);
                g2d.drawImage(IMAGE_RIGHT[currentImageMove], x, y, widthRight, heightRight, null);
                break;
        }
//        g2d.drawImage(IMAGE[orient],x,y,IMAGE[0].getWidth(null),IMAGE[0].getHeight(null),null);
    }

    public boolean drawDie(Graphics2D g2d, int t) {
        // ve animation chet
        if (t % 25 == 0) {
            currentImageDie++;
        }
        if (!checkSetXYDie) {
            x_tmp = x;
            y_tmp = y;
            checkSetXYDie = true;
        }
        if (currentImageDie < 11) {
            g2d.drawImage(IMAGE_DIE[currentImageDie], x_tmp, y_tmp - 60, null);
            return false;
        }
        if (currentImageDie == 11) {
            currentImageDie = 0;
            checkSetXYDie = false;
        }
        return true;
    }

    public void drawEffect(Graphics2D g2d, int t) {
        // hieu ung cua player
        if (t % animationTimeEffect == 0) {
            if (currentImageEffect < 7) {
                currentImageEffect++;
            } else {
                currentImageEffect = 0;
            }
        }

        g2d.drawImage(IMAGE_EFFECT[currentImageEffect], x + 5, y - 10, IMAGE_EFFECT[0].getWidth(null) - 10, IMAGE_EFFECT[0].getHeight(null) - 10, null);
    }

    public void changeOrient(int orient) {
        this.orient = orient;
    }

    public boolean checkMove(BitMap map) {
        // kiem tra buoc di tiep theo
        for (int i = 0; i < BitMap.SIZE; i++) {
            for (int j = 0; j < BitMap.SIZE; j++) {
                if (map.getPointImage(i, j).getBitValue() >= 0 && map.getPointImage(i, j).getBitValue() <= 4) {
                    Rectangle rectangle = getRectNextMove().intersection(map.getPointImage(i, j).getRect());
                    if (!rectangle.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void move(int t, BitMap map) {

        // di chuyen player
        try {
            if (t % timeMove != 0) {
                return;
            }
            pickItem(map);
            int xx = x;
            int yy = y;
            switch (orient) {
                case UP:
                    yy -= speed;

                    break;
                case DOWN:
                    yy += speed;

                    break;
                case LEFT:
                    xx -= speed;

                    break;
                case RIGHT:
                    xx += speed;

                    break;
            }
            xNext = xx;
            yNext = yy;
            boolean checkNextMove = checkMove(map);
            if (xx >= 0 && xx <= PlayedPanel.WIDTH - SIZE && yy >= 0 && yy <= PlayedPanel.HEIGHT - SIZE - 20 && checkNextMove) {
                x = xx;
                y = yy;
            }
        } catch (Exception e) {
        }
    }

    public void pickItem(BitMap bitMap) {
        // va cham giua player va item
        for (int i = 0; i < bitMap.SIZE; i++) {
            for (int j = 0; j < BitMap.SIZE; j++) {
                PointImage pointImage = bitMap.getPointImage(i, j);
                if (pointImage.getBitValue() >= 6 && pointImage.getBitValue() <= 8) {
                    Rectangle rectangle = getRect().intersection(pointImage.getRect());
                    if (!rectangle.isEmpty()) {
                        clipPickItem = SoundManager.getSound(getClass().getResource("/sounds/item.wav"));
                        clipPickItem.start();
                        switch (pointImage.getBitValue()) {
                            case PointImage.MAGICAL:
                                increaseStrength();
                                pointImage.setBitValue(PointImage.ROAD);
                                break;
                            case PointImage.BOOM:
                                increaseMaxBomb();
                                pointImage.setBitValue(PointImage.ROAD);
                                break;
                            case PointImage.HASTE:
                                increaseSpeed();
                                pointImage.setBitValue(PointImage.ROAD);
                                break;
                        }
                    }
                }
            }
        }
    }

    public boolean checkImpactZombie(ArrayList<Zombie> zombies) {
        // kiem tra va cham giua player va zombie
        for (int i = 0; i < zombies.size(); i++) {
            Rectangle rectangle = getRect().intersection(zombies.get(i).getRect());
            if (!rectangle.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkImpactWater(ArrayList<WaterBomb> waterBombs) {
        // kiem tra va cham giua player va nuoc bom
        for (int i = 0; i < waterBombs.size(); i++) {
            Rectangle rectangleUp = getRect().intersection(waterBombs.get(i).getRectUp());
            Rectangle rectangleDown = getRect().intersection(waterBombs.get(i).getRectDown());
            Rectangle rectangleLeft = getRect().intersection(waterBombs.get(i).getRectLeft());
            Rectangle rectangleRight = getRect().intersection(waterBombs.get(i).getRectRight());
            if (!rectangleUp.isEmpty() || !rectangleDown.isEmpty() || !rectangleLeft.isEmpty() || !rectangleRight.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Bomb plantBomb(int t) {
        if (t - time < timePlant) {
            return null;
        }
        if (maxBomb > 0) {
            time = t;
            int i = (x + SIZE / 2 - 6) / 45;
            int j = (y + SIZE / 2 - 15) / 45;
            Bomb bomb = new Bomb(i * 45, (j + 1) * 45, maxStrength);
            clipNewBomb = SoundManager.getSound(getClass().getResource("/sounds/newbomb.wav"));
            clipNewBomb.start();
            return bomb;
        } else {
            return null;
        }
    }

    public void descreaseMaxBomb() {
        maxBomb--;
    }

    public void increaseMaxBomb() {
        if (maxBomb < 9)
            maxBomb++;
    }

    public void increaseSpeed() {
        if (timeMove > 3) {
            timeMove--;
        }
        maxSpeed++;
    }

    public void increaseStrength() {
        if (maxStrength == 1) {
            maxStrength++;
        }
    }

    public void decreaseLife() {
        if (maxLife > 0) {
            maxLife--;
        }
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y + 30, 40, 36);
        return rectangle;
    }

    public Rectangle getRectNextMove() {
        Rectangle rectangle = new Rectangle(xNext + 9, yNext + 37, 42, 36);
        return rectangle;
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public int getSpeed() {
        return maxSpeed;
    }

    public int getMaxStrength() {
        return maxStrength;
    }

    public int getMaxLife() {
        return maxLife;
    }

}
