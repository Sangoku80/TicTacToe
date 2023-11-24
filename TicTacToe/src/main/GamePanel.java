package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel {

    private final Board board;
    private final ArrayList<Integer> arrows = new ArrayList<>();
    private int turn = 0;
    private final ArrayList<Integer> circles = new ArrayList<>();
    private final HashMap<Integer, String> symbols = new HashMap<>();
    public boolean gameOver = false;
    private final Game game;

    public GamePanel(Game game)
    {
        this.board = new Board();
        this.game = game;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(!circles.contains(board.touchBoard(e.getX(), e.getY())) && !arrows.contains(board.touchBoard(e.getX(), e.getY())) && !gameOver && game.running)
                {
                    turn += 1;

                    if(turn % 2 == 0)
                    {
                        arrows.add(board.touchBoard(e.getX(), e.getY()));
                        symbols.put(board.touchBoard(e.getX(), e.getY()), "arrows");
                    }
                    else
                    {
                        circles.add(board.touchBoard(e.getX(), e.getY()));
                        symbols.put(board.touchBoard(e.getX(), e.getY()), "circles");
                    }
                }

            }
        });
    }

    public int getXSymbol(int number)
    {
        int x;

        if(number <= 2)
        {
            x = number * 133 + 18;
        }
        else if(number <= 5)
        {
            x = (number-3) * 133 + 18;
        }
        else
        {
            x = (number-6) * 133 + 18;
        }

        return x;

    }

    public int getYSymbol(int number)
    {
        int y;

        if(number <= 2)
        {
            y = 18;
        }
        else if(number <= 5)
        {
            y = 133 + 18;
        }
        else
        {
            y = 266 + 18;
        }

        return y;
    }

    public boolean containsNumbers(ArrayList<Integer> symbol, int[] numbers)
    {

        int i = 0;

        for(int number : numbers)
        {
            if(symbol.contains(number))
            {
                i ++;

                if(i == numbers.length)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void isGameOver(Graphics g)
    {
        int[][] numbers = {
                {
                    0, 1, 2
                },

                {
                   3, 4, 5
                },

                {
                    6, 7, 8
                },

                {
                   0, 3, 6
                },

                {
                    1, 4, 7
                },

                {
                    2, 5, 8
                },

                {
                    0, 4, 8
                },

                {
                    6, 4, 2
                }
        };

        for(int j = 0; j < 8; j++)
        {
            if(symbols.size() == 9 && !containsNumbers(circles, numbers[j]) && !containsNumbers(arrows, numbers[j]))
            {
                gameOver = true;
                game.gameWindow.interfaceGameOver(true);
                g.drawString("Match nul", game.gameWindow.jFrame.getSize().width / 2 - 30, game.gameWindow.jFrame.getSize().height / 2 - 40);
            }
            else{
                if(containsNumbers(arrows, numbers[j]))
                {
                    gameOver = true;
                    game.gameWindow.interfaceGameOver(true);
                    g.drawString("Les croix ont gagné", game.gameWindow.jFrame.getSize().width / 2 - 55, game.gameWindow.jFrame.getSize().height / 2 - 40);
                }
                else if(containsNumbers(circles, numbers[j]))
                {
                    gameOver = true;
                    game.gameWindow.interfaceGameOver(true);
                    g.drawString("Les cercles ont gagné", game.gameWindow.jFrame.getSize().width / 2 - 60, game.gameWindow.jFrame.getSize().height / 2 - 40);
                }
            }

        }

    }

    public void restartGame()
    {
        arrows.clear();
        circles.clear();
        symbols.clear();
        gameOver = false;
    }

    public void drawCircle(Graphics g, int number) {

        g.drawOval(getXSymbol(number), getYSymbol(number), 100, 100);
    }

    public void drawArrow(Graphics g, int number)
    {
        g.drawLine(getXSymbol(number), getYSymbol(number), getXSymbol(number) + 100, getYSymbol(number) + 100);
        g.drawLine(getXSymbol(number), getYSymbol(number) + 100, getXSymbol(number) + 100, getYSymbol(number));

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        board.createBoard(g);

        for (int circle : circles)
        {
           drawCircle(g, circle);
        }

        for (int arrow : arrows)
        {
            drawArrow(g, arrow);
        }

        isGameOver(g);

    }

}
