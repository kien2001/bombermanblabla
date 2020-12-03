package com.t3h.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vaio on 23/08/2016.
 */
public class Clock {
    private final Image []IMAGE_NUMBER={
            new ImageIcon(getClass().getResource("/images/clock_0.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_6.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_7.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_8.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/clock_9.png")).getImage()
    };
    private int minutes,seconds;

    public Clock( int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void draw(Graphics2D g2d,int t){
        // tinh va ve cac so tuong ung voi thoi gian
        if(t%25==0){
            seconds++;
        }
        if(seconds>60){
            minutes++;
            seconds=0;
        }
        g2d.setColor(Color.yellow);
        g2d.drawString(".",832,31);
        g2d.drawString(".",832,38);
        g2d.drawRect(796,20,80,30);
        int tmpX=850,tmpY=25;
        switch (seconds%10){

            case 0:
                g2d.drawImage(IMAGE_NUMBER[0],tmpX,tmpY,null);
                break;
            case 1:
                g2d.drawImage(IMAGE_NUMBER[1],tmpX,tmpY,null);
                break;
            case 2:
                g2d.drawImage(IMAGE_NUMBER[2],tmpX,tmpY,null);
                break;
            case 3:
                g2d.drawImage(IMAGE_NUMBER[3],tmpX,tmpY,null);
                break;
            case 4:
                g2d.drawImage(IMAGE_NUMBER[4],tmpX,tmpY,null);
                break;
            case 5:
                g2d.drawImage(IMAGE_NUMBER[5],tmpX,tmpY,null);
                break;
            case 6:
                g2d.drawImage(IMAGE_NUMBER[6],tmpX,tmpY,null);
                break;
            case 7:
                g2d.drawImage(IMAGE_NUMBER[7],tmpX,tmpY,null);
                break;
            case 8:
                g2d.drawImage(IMAGE_NUMBER[8],tmpX,tmpY,null);
                break;
            case 9:
                g2d.drawImage(IMAGE_NUMBER[9],tmpX,tmpY,null);
                break;
        }
        tmpX=835;
        switch (seconds/10){
            case 0:
                g2d.drawImage(IMAGE_NUMBER[0],tmpX,tmpY,null);
                break;
            case 1:
                g2d.drawImage(IMAGE_NUMBER[1],tmpX,tmpY,null);
                break;
            case 2:
                g2d.drawImage(IMAGE_NUMBER[2],tmpX,tmpY,null);
                break;
            case 3:
                g2d.drawImage(IMAGE_NUMBER[3],tmpX,tmpY,null);
                break;
            case 4:
                g2d.drawImage(IMAGE_NUMBER[4],tmpX,tmpY,null);
                break;
            case 5:
                g2d.drawImage(IMAGE_NUMBER[5],tmpX,tmpY,null);
                break;
            case 6:
                g2d.drawImage(IMAGE_NUMBER[6],tmpX,tmpY,null);
                break;
            case 7:
                g2d.drawImage(IMAGE_NUMBER[7],tmpX,tmpY,null);
                break;
            case 8:
                g2d.drawImage(IMAGE_NUMBER[8],tmpX,tmpY,null);
                break;
            case 9:
                g2d.drawImage(IMAGE_NUMBER[9],tmpX,50,null);
                break;
        }
        tmpX=815;
        switch (minutes%10){
            case 0:
                g2d.drawImage(IMAGE_NUMBER[0],tmpX,tmpY,null);
                break;
            case 1:
                g2d.drawImage(IMAGE_NUMBER[1],tmpX,tmpY,null);
                break;
            case 2:
                g2d.drawImage(IMAGE_NUMBER[2],tmpX,tmpY,null);
                break;
            case 3:
                g2d.drawImage(IMAGE_NUMBER[3],tmpX,tmpY,null);
                break;
            case 4:
                g2d.drawImage(IMAGE_NUMBER[4],tmpX,tmpY,null);
                break;
            case 5:
                g2d.drawImage(IMAGE_NUMBER[5],tmpX,tmpY,null);
                break;
            case 6:
                g2d.drawImage(IMAGE_NUMBER[6],tmpX,tmpY,null);
                break;
            case 7:
                g2d.drawImage(IMAGE_NUMBER[7],tmpX,tmpY,null);
                break;
            case 8:
                g2d.drawImage(IMAGE_NUMBER[8],tmpX,tmpY,null);
                break;
            case 9:
                g2d.drawImage(IMAGE_NUMBER[9],tmpX,tmpY,null);
                break;
        }
        tmpX=800;
        switch (minutes/10){
            case 0:
                g2d.drawImage(IMAGE_NUMBER[0],tmpX,tmpY,null);
                break;
            case 1:
                g2d.drawImage(IMAGE_NUMBER[1],tmpX,tmpY,null);
                break;
            case 2:
                g2d.drawImage(IMAGE_NUMBER[2],tmpX,tmpY,null);
                break;
            case 3:
                g2d.drawImage(IMAGE_NUMBER[3],tmpX,tmpY,null);
                break;
            case 4:
                g2d.drawImage(IMAGE_NUMBER[4],tmpX,tmpY,null);
                break;
            case 5:
                g2d.drawImage(IMAGE_NUMBER[5],tmpX,tmpY,null);
                break;
            case 6:
                g2d.drawImage(IMAGE_NUMBER[6],tmpX,tmpY,null);
                break;
            case 7:
                g2d.drawImage(IMAGE_NUMBER[7],tmpX,tmpY,null);
                break;
            case 8:
                g2d.drawImage(IMAGE_NUMBER[8],tmpX,tmpY,null);
                break;
            case 9:
                g2d.drawImage(IMAGE_NUMBER[9],tmpX,tmpY,null);
                break;
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
