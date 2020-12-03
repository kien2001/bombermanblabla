package com.t3h.model;


import com.t3h.gui.MyFrame;
import com.t3h.gui.PlayedPanel;
import sounds.SoundManager;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by vaio on 09/08/2016.
 */
public class GameManager {
    public static final String PATH_MAP = "G:/boommap.txt";
    private final Image IMAGE_INFO = new ImageIcon(getClass().getResource("/images/background_Play.png")).getImage();
    private final Image[] IMAGE_WIN = {
            new ImageIcon(getClass().getResource("/images/w.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/i.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/n.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/!!.png")).getImage()
    };
    private final Image[] IMAGE_LOSE = {
            new ImageIcon(getClass().getResource("/images/l.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/o.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/s.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/e.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/!!_black.png")).getImage()
    };
    private int countWin = 0;
    private int countLose = 0;
    private Player player;
    private BitMap bitMap;
    private InfoGaming infoGaming;
    private ArrayList<Bomb> bombs;
    private ArrayList<Zombie> zombies;
    private ArrayList<WaterBomb> waterBombs;
    private Clock clock;
    private boolean checkDrawDie;
    private Clip clipBangBomb;
    private Clip clipZombieDie;
    private Clip clipWin;
    private Clip clipLose;
    private Comparator comparator = new Comparator<Zombie>() {
        @Override
        public int compare(Zombie o1, Zombie o2) {
            if (o1.getY() > o2.getY()) {
                return 1;
            } else if (o1.getY() < o2.getY()) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public GameManager() {
        loadMap();
        initGame();
        clipWin = SoundManager.getSound(getClass().getResource("/sounds/win.wav"));
        clipLose = SoundManager.getSound(getClass().getResource("/sounds/lose.wav"));

    }

    public void initGame() {
        clock = new Clock(0, 0);
        infoGaming = new InfoGaming();
        bombs = new ArrayList<Bomb>();
        waterBombs = new ArrayList<WaterBomb>();
        zombies = new ArrayList<Zombie>();
        player = new Player(PlayedPanel.WIDTH / 2 - Player.SIZE / 2, PlayedPanel.HEIGHT / 2 - player.SIZE / 2, Player.DOWN);
        Zombie zombie = new Zombie(350, 105, 0);
        Zombie zombie1 = new Zombie(350, 550, 0);
        Zombie zombie2 = new Zombie(40, 50, 1);
        Zombie zombie3 = new Zombie(40, 640, 0);
        Zombie zombie4 = new Zombie(665, 655, 2);
        Zombie zombie5 = new Zombie(670, 40, 1);
        zombies.add(zombie);
        zombies.add(zombie1);
        zombies.add(zombie2);
        zombies.add(zombie3);
        zombies.add(zombie4);
        zombies.add(zombie5);

    }

    public void loadMap() {
        bitMap = new BitMap(PATH_MAP);
        bitMap.readingFile();
    }

    public void draw(Graphics2D g2d, int t, boolean checkPressMove) {

        g2d.drawImage(IMAGE_INFO, 0, 0, MyFrame.WIDTH, MyFrame.HEIGHT, null);
        for (int i = 0; i < waterBombs.size(); i++) {
            waterBombs.get(i).draw(g2d);
        }
        bitMap.drawMap(g2d);
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).draw(g2d, t);
        }
        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).draw(g2d, t);
        }
        if (player.checkImpactZombie(zombies) || player.checkImpactWater(waterBombs)) {
            checkDrawDie = true;
        }
        if (checkDrawDie) {
            if (player.drawDie(g2d, t)) {
                player.decreaseLife();
                player.setLocation(PlayedPanel.WIDTH / 2 - Player.SIZE / 2, PlayedPanel.HEIGHT / 2 - player.SIZE / 2);
                checkDrawDie = false;
            }
        } else {
            if (checkPressMove) {
                player.drawDinamic(g2d, t);
            } else {
                player.drawStatic(g2d);
            }
        }
        player.drawEffect(g2d, t);
        bitMap.drawShadowMap(g2d);
        infoGaming.draw(g2d);
        clock.draw(g2d, t);
        infoGaming.update(player.getMaxLife(), player.getMaxStrength(), player.getMaxBomb(), player.getSpeed());
    }

    public boolean drawWin(Graphics2D g2d, int t) {
        // ve bao hieu win
        if (!clipWin.isRunning() && clipWin != null) {

            clipWin.start();
        }
        if (t % 150 == 0) {
            countWin++;
        }
        if (countWin == 1) {
            g2d.drawImage(IMAGE_WIN[0], 200, 300, null);
        }
        if (countWin == 2) {
            g2d.drawImage(IMAGE_WIN[0], 200, 300, null);
            g2d.drawImage(IMAGE_WIN[1], 300, 300, null);

        }
        if (countWin == 3) {
            g2d.drawImage(IMAGE_WIN[0], 200, 300, null);
            g2d.drawImage(IMAGE_WIN[1], 300, 300, null);
            g2d.drawImage(IMAGE_WIN[2], 330, 300, null);
        }
        if (countWin == 4) {
            g2d.drawImage(IMAGE_WIN[0], 200, 300, null);
            g2d.drawImage(IMAGE_WIN[1], 300, 300, null);
            g2d.drawImage(IMAGE_WIN[2], 330, 300, null);
            g2d.drawImage(IMAGE_WIN[3], 400, 300, null);
        }
        if (countWin == 5) {
            return true;
        }
        return false;
    }

    public boolean drawLose(Graphics2D g2d, int t) {
        // ve bao hieu lose
        if (!clipLose.isRunning() && clipWin != null) {

            clipLose.start();
        }
        if (t % 150 == 0) {
            countLose++;
        }
        if (countLose == 1) {
            g2d.drawImage(IMAGE_LOSE[0], 200, 300, null);
        }
        if (countLose == 2) {
            g2d.drawImage(IMAGE_LOSE[0], 200, 300, null);
            g2d.drawImage(IMAGE_LOSE[1], 300, 300, null);

        }
        if (countLose == 3) {
            g2d.drawImage(IMAGE_LOSE[0], 200, 300, null);
            g2d.drawImage(IMAGE_LOSE[1], 300, 300, null);
            g2d.drawImage(IMAGE_LOSE[2], 400, 300, null);
        }
        if (countLose == 4) {
            g2d.drawImage(IMAGE_LOSE[0], 200, 300, null);
            g2d.drawImage(IMAGE_LOSE[1], 300, 300, null);
            g2d.drawImage(IMAGE_LOSE[2], 400, 300, null);
            g2d.drawImage(IMAGE_LOSE[3], 500, 300, null);
        }
        if (countLose == 5) {
            g2d.drawImage(IMAGE_LOSE[0], 200, 300, null);
            g2d.drawImage(IMAGE_LOSE[1], 300, 300, null);
            g2d.drawImage(IMAGE_LOSE[2], 400, 300, null);
            g2d.drawImage(IMAGE_LOSE[3], 500, 300, null);
            g2d.drawImage(IMAGE_LOSE[4], 600, 300, null);
        }
        if (countLose == 6) {
            return true;
        }
        return false;
    }

    public void movePlayer(int t, int orient) {
        player.changeOrient(orient);
        player.move(t, bitMap);
    }

    public void plantBomb(int t) {
        // dat bom
        Bomb bomb = player.plantBomb(t);
        if (bomb != null && !isExistBomb(bomb.getX(), bomb.getY())) {
            bombs.add(bomb);
            player.descreaseMaxBomb();
        }
    }

    public boolean isExistBomb(int x, int y) {
        // neu ton tai bom thi khong dat them nua
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).getX() == x && bombs.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void AI(int t) {
        //  cho bom no
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).bangTimeArrive(t)) {
                clipBangBomb = SoundManager.getSound(getClass().getResource("/sounds/bomb_bang.wav"));
                clipBangBomb.start();
                waterBombs.add(bombs.get(i).bang(t, bitMap));
                bombs.remove(i);
                player.increaseMaxBomb();
            }
        }
        // xoa nuoc khi het thoi gian va thay doi map khi cham nuoc
        for (int i = 0; i < waterBombs.size(); i++) {
            WaterBomb waterBomb = waterBombs.get(i);
            if (waterBomb.isVisible(t)) {
                bitMap.changeMapBombBang(waterBomb);

            } else {
                waterBombs.remove(i);
            }
        }
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.get(i).checkDie(waterBombs)) {
                Clip clip = SoundManager.getSound(getClass().getResource("/sounds/monster_die.wav"));
                clip.start();
                zombies.remove(i);
            }
        }
        // va cham no bom voi bom
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).bangByBomb(waterBombs)) {
                WaterBomb waterBomb = bombs.get(i).bang(t, bitMap);
                waterBombs.add(waterBomb);
                bombs.remove(i);
                player.increaseMaxBomb();

            }
        }// cho zombie di chuyen
        for (int i = 0; i < zombies.size(); i++) {
            Random random = new Random();
            int rand = random.nextInt(100);
            if (rand > 99) {
                zombies.get(i).changOrient(random.nextInt(4));
            }
            zombies.get(i).move(t, bitMap, bombs);
        }
        // kiem tra va cham zzombie va nuoc
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.get(i).checkDie(waterBombs)) {
                clipZombieDie = SoundManager.getSound(getClass().getResource("/sounds/monster_die.wav"));
                clipZombieDie.start();
                zombies.remove(i);
            }
        }
        //

        // sap xep zombie
        Collections.sort(zombies, comparator);
    }

    public int getNumberOfZombie() {
        return zombies.size();
    }

    public int getPlayerLife() {
        return player.getMaxLife();
    }

    public String getTimeClock() {
        return clock.getMinutes() + " : " + clock.getSeconds();
    }


}
