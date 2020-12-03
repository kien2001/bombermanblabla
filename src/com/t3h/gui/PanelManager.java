package com.t3h.gui;

import sounds.SoundManager;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by vaio on 09/08/2016.
 */
class PanelManager extends JPanel implements ActionListener, KeyListener {
    public static final int W_BUTTON = 150;
    public static final int H_BUTTON = 50;
    private CardLayout cardLayout = new CardLayout();
    private PlayedPanel playedPanel;
    private OptionPanel optionPanel;
    private StartMenuPanel startMenuPanel;
    private JButton btnPlay, btnOption, btnExit, btnExit2;
    private final ImageIcon imagePlay = new ImageIcon(getClass().getResource("/images/Play.png"));
    private final ImageIcon imagePlay2 = new ImageIcon(getClass().getResource("/images/Play2.png"));
    private final ImageIcon imageOption = new ImageIcon(getClass().getResource("/images/Option.png"));
    private final ImageIcon imageOption2 = new ImageIcon(getClass().getResource("/images/Option2.png"));
    private final ImageIcon imageExit = new ImageIcon(getClass().getResource("/images/Exit.png"));
    private final ImageIcon imageExit2 = new ImageIcon(getClass().getResource("/images/Exit2.png"));

    public PanelManager() {
        setLayout(cardLayout);
        playedPanel = new PlayedPanel(cardLayout);
        startMenuPanel = new StartMenuPanel();
        optionPanel = new OptionPanel();
        add(startMenuPanel, "StartMenuPanel");
        add(optionPanel, "OptionPanel");
        addKeyListener(this);
        setFocusable(true);
        initComponent();

    }

    private void initComponent() {
        // phim play trong menu chinh
        btnPlay = new JButton(imagePlay);
        btnPlay.setSize(W_BUTTON, H_BUTTON);
        btnPlay.setLocation(210, 590);
        btnPlay.setActionCommand("Play");
        btnPlay.addActionListener(this);
        btnPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPlay.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnPlay.setIcon(imagePlay2);
                Clip clip = SoundManager.getSound(getClass().getResource("/sounds/button.wav"));
                clip.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPlay.setIcon(imagePlay);
            }
        });
        // phim option trong menu chinh
        btnOption = new JButton(imageOption);
        btnOption.setSize(W_BUTTON, H_BUTTON);
        btnOption.setLocation(410, 590);
        btnOption.setActionCommand("Option");
        btnOption.addActionListener(this);
        btnOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOption.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnOption.setIcon(imageOption2);
                Clip clip = SoundManager.getSound(getClass().getResource("/sounds/button.wav"));
                clip.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnOption.setIcon(imageOption);
            }
        });
        // phim exit trong menu chinh
        btnExit = new JButton(imageExit);
        btnExit.setSize(W_BUTTON, H_BUTTON);
        btnExit.setLocation(610, 590);
        btnExit.setActionCommand("Exit");
        btnExit.addActionListener(this);
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnExit.setIcon(imageExit2);
                Clip clip = SoundManager.getSound(getClass().getResource("/sounds/button.wav"));
                clip.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setIcon(imageExit);
            }
        });
        // phim exit trong panel choi game
        btnExit2 = new JButton(imageExit);
        btnExit2.setSize(W_BUTTON, H_BUTTON);
        btnExit2.setLocation(MyFrame.WIDTH / 2 + 310, 700);
        btnExit2.setActionCommand("Exit2");
        btnExit2.addActionListener(this);
        btnExit2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExit2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnExit2.setIcon(imageExit2);
                Clip clip = SoundManager.getSound(getClass().getResource("/sounds/button.wav"));
                clip.start();
            }


            @Override
            public void mouseExited(MouseEvent e) {
                btnExit2.setIcon(imageExit);
            }
        });

        startMenuPanel.add(btnPlay);
        startMenuPanel.add(btnOption);
        startMenuPanel.add(btnExit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // cai dat nhan button
        switch (e.getActionCommand()) {
            case "Play":
                playedPanel = new PlayedPanel(cardLayout);
                add(playedPanel, "PlayedPanel");
                cardLayout.show(this, "PlayedPanel");
                playedPanel.add(btnExit2);
                playedPanel.requestFocus();
                int resultPlay = JOptionPane.showConfirmDialog(this, "Bạn đã sẵn sàng chưa ? ", "Get Ready!", JOptionPane.YES_NO_OPTION);
                if (!(resultPlay == JOptionPane.YES_OPTION)) {
                    cardLayout.show(this, "StartMenuPanel");
                }
                break;
            case "Option":
                cardLayout.show(this, "OptionPanel");
                optionPanel.setCardLayout(cardLayout);
                optionPanel.requestFocus();
                break;
            case "Exit":
                int resultExit = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn thoát ? ", "Exit", JOptionPane.YES_NO_OPTION);
                if (resultExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            case "Exit2":
                int resultExit2 = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn thoát ? ", "Exit", JOptionPane.YES_NO_OPTION);
                if (resultExit2 == JOptionPane.YES_OPTION) {
                    cardLayout.show(this, "StartMenuPanel");
                    startMenuPanel.requestFocus();
                    startMenuPanel.playSound();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
