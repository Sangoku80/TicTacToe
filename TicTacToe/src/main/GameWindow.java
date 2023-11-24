package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    public JFrame jFrame;
    public JButton button_restart;
    public JButton button_start;

    public GameWindow(Game game)
    {
        this.button_restart = new JButton("Replay");
        this.button_start = new JButton("Play");

        jFrame = new JFrame();
        jFrame.setSize(410, 430);
        jFrame.setResizable(false);

        game.gamePanel.setBackground(Color.black);
        jFrame.setContentPane(game.gamePanel);

        // buttons
        button_restart.setBounds(150, 190, 100, 30);
        button_start.setBounds(150, 190, 100, 30);

        // buttons actions
        button_restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.gamePanel.restartGame();
                interfaceGameOver(false);
            }
        });

        button_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGameLoop();
                button_start.setVisible(false);
            }
        });

        // add the buttons
        jFrame.getContentPane().add(button_restart);
        jFrame.getContentPane().add(button_start);

        button_restart.setVisible(false);
        button_start.setVisible(true);

        jFrame.setLayout(null);
        jFrame.setVisible(true);

    }

    public void interfaceGameOver(boolean visible)
    {
        button_restart.setVisible(visible);
    }
}
