package com.t3h.model;

import com.t3h.gui.PlayedPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vaio on 17/08/2016.
 */
public class Zombie {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SIZE = 45;
    private int x, y;
    private int orient;
    private int speed = 3;
    private int xNext;
    private int yNext;
    private int timeMove = 5;
    private int currentImageMove = 0;
    private int animationTime = 20; //  draw()
    private final Image[] IMAGE_UP = {
            new ImageIcon(getClass().getResource("/images/monster_up1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_up2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_up3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_up4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_up5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_up6.png")).getImage()

    };
    private final Image[] IMAGE_DOWN = {
            new ImageIcon(getClass().getResource("/images/monster_down1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_down2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_down3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_down4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_down5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_down6.png")).getImage()
    };
    private final Image[] IMAGE_LEFT = {

            new ImageIcon(getClass().getResource("/images/monster_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_left3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_left4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_left5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_left6.png")).getImage()

    };
    private final Image[] IMAGE_RIGHT = {
            new ImageIcon(getClass().getResource("/images/monster_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_right2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_right3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_right4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_right5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/monster_right6.png")).getImage()
    };

    public Zombie(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    public void draw(Graphics2D g2d, int t) {
        if (t % animationTime == 0) {
            if (currentImageMove < 5) {
                currentImageMove++;
            } else {
                currentImageMove = 0;
            }

        }
        switch (orient) {
            case UP:
                g2d.drawImage(IMAGE_UP[currentImageMove], x, y,
                        IMAGE_UP[currentImageMove].getWidth(null), IMAGE_UP[currentImageMove].getHeight(null), null);
                break;
            case DOWN:
                g2d.drawImage(IMAGE_DOWN[currentImageMove], x, y,
                        IMAGE_DOWN[currentImageMove].getWidth(null), IMAGE_DOWN[currentImageMove].getHeight(null), null);
                break;
            case LEFT:
                g2d.drawImage(IMAGE_LEFT[currentImageMove], x, y,
                        IMAGE_LEFT[currentImageMove].getWidth(null), IMAGE_LEFT[currentImageMove].getHeight(null), null);
                break;
            case RIGHT:
                g2d.drawImage(IMAGE_RIGHT[currentImageMove], x, y,
                        IMAGE_RIGHT[currentImageMove].getWidth(null), IMAGE_RIGHT[currentImageMove].getHeight(null), null);
                break;

        }
    }

    public boolean checkMoveInMap(BitMap map) {
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

    public boolean checkImpactBoom(ArrayList<Bomb> bombs) {
        // kiem tra va cham voi bom
        for (int i = 0; i < bombs.size(); i++) {
            Rectangle rectangle = getRectNextMove().intersection(bombs.get(i).getRect());
            if (!rectangle.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void turnAround() {
        // quay dau tro lai khi gap vat can
        Random random = new Random();
        switch (orient) {
            case UP:
                int r = random.nextInt(3);
                if (r == 0) {
                    orient = LEFT;
                } else if (r == 1) {
                    orient = RIGHT;
                } else {
                    orient = DOWN;
                }
                break;
            case DOWN:
                int r2 = random.nextInt(3);
                if (r2 == 0) {
                    orient = LEFT;
                } else if (r2 == 1) {
                    orient = RIGHT;
                } else {
                    orient = UP;
                }
                break;
            case LEFT:
                int r3 = random.nextInt(3);
                if (r3 == 0) {
                    orient = UP;
                } else if (r3 == 1) {
                    orient = DOWN;
                } else {
                    orient = RIGHT;
                }
                break;
            case RIGHT:
                int r4 = random.nextInt(3);
                if (r4 == 0) {
                    orient = UP;
                } else if (r4 == 1) {
                    orient = DOWN;
                } else {
                    orient = LEFT;
                }
                break;
        }
    }

    public void move(int t, BitMap map, ArrayList<Bomb> bombs) {
        // di chuyen zombie
        try {
            if (t % timeMove != 0) {
                return;
            }
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
            boolean checkNextMove = checkMoveInMap(map) && checkImpactBoom(bombs);
            if (xx >= 0 && xx <= PlayedPanel.WIDTH - SIZE && yy >= 0 && yy <= PlayedPanel.HEIGHT - SIZE - 20 && checkNextMove) {
                x = xx;
                y = yy;
            } else {
                turnAround();
            }
        } catch (Exception e) {
        }
    }

    public void changOrient(int orient) {
        this.orient = orient;
    }

    public boolean checkDie(ArrayList<WaterBomb> waterBombs) {
        // va cham giua zombie va nuoc bom
        for (int i = 0; i < waterBombs.size(); i++) {
            Rectangle rectangleUp = getRect().intersection(waterBombs.get(i).getRectUp());
            Rectangle rectangleDown = getRect().intersection(waterBombs.get(i).getRectDown());
            Rectangle rectangleLeft = getRect().intersection(waterBombs.get(i).getRectLeft());
            Rectangle rectangleRight = getRect().intersection(waterBombs.get(i).getRectRight());
            if ((!rectangleUp.isEmpty()) || (!rectangleDown.isEmpty()) || (!rectangleLeft.isEmpty()) || (!rectangleRight.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getRectNextMove() {
        Rectangle rectangle = new Rectangle(xNext + 10, yNext + 30, 40, 25);
        return rectangle;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x + 10, y, 40, 50);
        return rectangle;
    }

    public int getY() {
        return y;
    }
}
