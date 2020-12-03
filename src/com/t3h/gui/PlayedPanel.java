package com.t3h.gui;

import com.t3h.model.GameManager;
import com.t3h.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

/**
 * Created by vaio on 09/08/2016.
 */
public class PlayedPanel extends JPanel implements KeyListener, Runnable {
    public static final int WIDTH = 765;
    public static final int HEIGHT = 765;
    private CardLayout parentCardLayout;
    private GameManager gameManager = new GameManager();
    private BitSet bitSet = new BitSet(256);
    private boolean isRunning = true;
    private Thread thread = new Thread(this);
    private int t = 0;
    private boolean checkPressMove;
    private boolean checkWin;
    private boolean checkLose;

    public PlayedPanel(CardLayout cardLayout) {
        parentCardLayout = cardLayout;
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        gameManager.draw(g2d, t, checkPressMove); // ve cac doi tuong ra
        if (gameManager.getNumberOfZombie() == 0) {
            checkWin = gameManager.drawWin(g2d, t); //  ve win
        }
        if (gameManager.getPlayerLife() == 0) {
            checkLose = gameManager.drawLose(g2d, t); // ve lose
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // cai dat phim esc de thoat
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            int status = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không ? ", "", JOptionPane.YES_NO_OPTION);
            if (status == JOptionPane.YES_OPTION) {
                parentCardLayout.show(getParent(), "StartMenuPanel");
                createGame();
            }
        }
        // cai dat phim multi press
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
            checkPressMove = true;

        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // kiem tra nha phim cach
        if (e.getKeyCode() != KeyEvent.VK_SPACE) {
            checkPressMove = false;
        }
        // xoa bitset
        bitSet.clear(e.getKeyCode());
    }

    @Override
    public void run() {
        while (isRunning) {
            t++;
            // cho player di chuyen
            if (bitSet.get(KeyEvent.VK_UP)) {
                gameManager.movePlayer(t, Player.UP);
            } else if (bitSet.get(KeyEvent.VK_DOWN)) {
                gameManager.movePlayer(t, Player.DOWN);
            } else if (bitSet.get(KeyEvent.VK_LEFT)) {
                gameManager.movePlayer(t, Player.LEFT);
            } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
                gameManager.movePlayer(t, Player.RIGHT);
            }
            // cho player dat bom
            if (bitSet.get(KeyEvent.VK_SPACE)) {
                gameManager.plantBomb(t);
            }
            // cho cac doi tuong khac tu hoat dong
            gameManager.AI(t);
            repaint();
            // tao moi game neu thang hoac thua
            if (checkLose) {
                int result = JOptionPane.showConfirmDialog(null, " Bạn có muốn chơi lại không ? ", "retry", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    createGame();
                    bitSet.clear();
                } else {
                    parentCardLayout.show(getParent(), "StartMenuPanel");
                    createGame();
                    bitSet.clear();
                }
                checkLose = false;
            }
            if (checkWin) {
                int result2 = JOptionPane.showConfirmDialog(null, "Thời gian hoàn thành : " + gameManager.getTimeClock() +
                        "\nBạn có muốn chơi lại không ? ", "retry", JOptionPane.YES_NO_OPTION);
                if (result2 == JOptionPane.YES_OPTION) {
                    createGame();
                } else {
                    parentCardLayout.show(getParent(), "StartMenuPanel");
                    createGame();
                }
                checkWin = false;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createGame() {
        gameManager = new GameManager();
    }

}
