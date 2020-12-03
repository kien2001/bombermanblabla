package com.t3h.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vaio on 09/08/2016.
 */
public class BitMap {
    public static final int SIZE = 17;
    public static final int W_IMAGE = 45;
    public static final int H_IMAGE = 45;
    private boolean checkOnlyMagical;
    private String path;
    private ArrayList<PointImage>[] points;
    private int line;

    public BitMap(String path) {
        this.path = path;
        line = 0;
        points = new ArrayList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            points[i] = new ArrayList<PointImage>();
        }
    }

    public void readingFile() {
        // doc file map
        File file = new File(path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String string = br.readLine();
            while (string != null) {
                for (int i = 0; i < string.length(); i++) {
                    char c = string.charAt(i);
                    int bit = c - '0';
                    int x = i * W_IMAGE;
                    int y = line * H_IMAGE;
                    PointImage point = new PointImage(x, y, bit);
                    points[line].add(point);
                }
                line++;
                string = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void drawMap(Graphics2D g2d) {
        // ve map
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < points[i].size(); j++) {
                points[i].get(j).drawPoint(g2d);
            }
        }
    }

    public void drawShadowMap(Graphics2D g2d) {
        // tao hieu ung dung sau vat the
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < points[i].size(); j++) {
                points[i].get(j).drawShadow(g2d);
            }
        }
    }

    public PointImage getPointImage(int i, int j) {
        return points[i].get(j);
    }

    public void changeMapBombBang(WaterBomb waterBomb) {
        // cho va cham giua map va nuoc bom
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                PointImage pointImage = points[i].get(j);
                if (pointImage.getBitValue() == 3) {
                    Rectangle rectangleUp = pointImage.getRect().intersection(waterBomb.getRectUp());
                    Rectangle rectangleDown = pointImage.getRect().intersection(waterBomb.getRectDown());
                    Rectangle rectangleLeft = pointImage.getRect().intersection(waterBomb.getRectLeft());
                    Rectangle rectangleRight = pointImage.getRect().intersection(waterBomb.getRectRight());
                    if (!rectangleDown.isEmpty() || !rectangleUp.isEmpty() || !rectangleRight.isEmpty() || !rectangleLeft.isEmpty()) {
                        Random random = new Random();
                        int rand = random.nextInt(100);
                        if (rand > 60) {
                            if (!checkOnlyMagical) {
                                int r = random.nextInt(3) + 6;
                                if (r == PointImage.MAGICAL) {
                                    checkOnlyMagical = true;
                                    pointImage.setBitValue(PointImage.MAGICAL);
                                } else {
                                    int r2 = random.nextInt(2) + 7;
                                    pointImage.setBitValue(r2);

                                }
                            } else {
                                int r2 = random.nextInt(2) + 7;
                                pointImage.setBitValue(r2);
                            }
                        } else {
                            pointImage.setBitValue(PointImage.ROAD);
                        }

                    }
                }
            }
        }
    }

}
