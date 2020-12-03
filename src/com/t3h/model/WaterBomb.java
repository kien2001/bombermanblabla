package com.t3h.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vaio on 15/08/2016.
 */
public class WaterBomb {
    public static final int UP_1 = 0;
    public static final int UP_2 = 1;
    public static final int DOWN_1 = 2;
    public static final int DOWN_2 = 3;
    public static final int LEFT_1 = 4;
    public static final int LEFT_2 = 5;
    public static final int RIGHT_1 = 6;
    public static final int RIGHT_2 = 7;
    private int x, y;
    private int damage = 1;
    private int duration = 100;
    private int time;
    private boolean bitCheck[][] = new boolean[2][4];

    private final Image[] IMAGE = {
            new ImageIcon(getClass().getResource("/images/bombbang_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_up_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_down_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_down_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_left_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/bombbang_right_2.png")).getImage()

    };

    public WaterBomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        // ve nuoc bom da duoc tinh toan
        if (damage == 1) {
            if (bitCheck[0][0]) {
                g2d.drawImage(IMAGE[UP_1], x, y - damage * 45, null);
            }
            if (bitCheck[0][1]) {
                g2d.drawImage(IMAGE[DOWN_1], x, y, null);
            }
            if (bitCheck[0][2]) {
                g2d.drawImage(IMAGE[LEFT_1], x - damage * 45, y, null);
            }
            if (bitCheck[0][3]) {
                g2d.drawImage(IMAGE[RIGHT_1], x, y, null);
            }
        } else {
            if (bitCheck[0][0]) {
                if (bitCheck[1][0]) {
                    g2d.drawImage(IMAGE[UP_2], x, y - damage * 45, null);
                } else {
                    g2d.drawImage(IMAGE[UP_1], x, y - damage * 45 + 45, null);
                }
            }
            if (bitCheck[0][1]) {
                if (bitCheck[1][1]) {
                    g2d.drawImage(IMAGE[DOWN_2], x, y, null);
                } else {
                    g2d.drawImage(IMAGE[DOWN_1], x, y, null);
                }
            }
            if (bitCheck[0][2]) {
                if (bitCheck[1][2]) {
                    g2d.drawImage(IMAGE[LEFT_2], x - damage * 45, y, null);
                } else {
                    g2d.drawImage(IMAGE[LEFT_1], x - damage * 45 + 45, y, null);
                }
            }
            if (bitCheck[0][3]) {
                if (bitCheck[1][3]) {
                    g2d.drawImage(IMAGE[RIGHT_2], x, y, null);
                } else {
                    g2d.drawImage(IMAGE[RIGHT_1], x, y, null);
                }
            }
        }


    }

    public void checkDraw(BitMap bitMap) {
        // tinh khoang cach ve nuoc bom
        bitCheck[1][0] = true;
        bitCheck[1][1] = true;
        bitCheck[1][2] = true;
        bitCheck[1][3] = true;
        PointImage pointImageUp = bitMap.getPointImage(y / 45 - 1, x / 45);
        if (pointImageUp.getBitValue() == 5 || pointImageUp.getBitValue() == 3) {
            bitCheck[0][0] = true;
            if (pointImageUp.getBitValue() == 3 || pointImageUp.getBitValue() >= 6) {
                bitCheck[1][0] = false;
            } else {
                bitCheck[1][0] = true;
            }
        }
        PointImage pointImageDown = bitMap.getPointImage(y / 45 + 1, x / 45);
        if (pointImageDown.getBitValue() >= 5 || pointImageDown.getBitValue() == 3) {
            bitCheck[0][1] = true;
            if (pointImageDown.getBitValue() == 3 || pointImageDown.getBitValue() >= 6) {
                bitCheck[1][1] = false;
            } else {
                bitCheck[1][1] = true;
            }
        }

        PointImage pointImageLeft = bitMap.getPointImage(y / 45, x / 45 - 1);
        if (pointImageLeft.getBitValue() >= 5 || pointImageLeft.getBitValue() == 3) {
            bitCheck[0][2] = true;
            if (pointImageLeft.getBitValue() == 3 || pointImageLeft.getBitValue() >= 6) {
                bitCheck[1][2] = false;
            } else {
                bitCheck[1][2] = true;
            }
        }
        PointImage pointImageRight = bitMap.getPointImage(y / 45, x / 45 + 1);
        if (pointImageRight.getBitValue() >= 5 || pointImageRight.getBitValue() == 3) {
            bitCheck[0][3] = true;
            if (pointImageRight.getBitValue() == 3 || pointImageRight.getBitValue() >= 6) {
                bitCheck[1][3] = false;
            } else {
                bitCheck[1][3] = true;
            }
        }
    }

    public void setStartTime(int t) {
        time = t;
    }

    public boolean isVisible(int t) {
        // kiem tra xem nuoc bom da het thoi gian chua
        if (t > time + duration) {
            return false;
        }
        return true;
    }

    public Rectangle getRectUp() {
        Rectangle rectangle;
        if (damage == 2 && bitCheck[0][0] && bitCheck[1][0]) {
            rectangle = new Rectangle(x, y - 90, 45, 135);
        } else {
            rectangle = new Rectangle(x, y - 45, 45, 90);
        }
        return rectangle;
    }

    public Rectangle getRectDown() {
        Rectangle rectangle;
        if (damage == 2 && bitCheck[0][1] && bitCheck[1][1]) {
            rectangle = new Rectangle(x, y, 45, 135);
        } else {
            rectangle = new Rectangle(x, y, 45, 90);
        }
        return rectangle;
    }

    public Rectangle getRectLeft() {
        Rectangle rectangle;
        if (damage == 2 && bitCheck[0][2] && bitCheck[1][2]) {
            rectangle = new Rectangle(x - 89, y, 135, 45);
        } else {
            rectangle = new Rectangle(x - 44, y, 90, 45);
        }
        return rectangle;
    }

    public Rectangle getRectRight() {
        Rectangle rectangle;
        if (damage == 2 && bitCheck[0][3] && bitCheck[1][3]) {
            rectangle = new Rectangle(x, y, 135, 45);
        } else {
            rectangle = new Rectangle(x, y, 90, 45);
        }
        return rectangle;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
