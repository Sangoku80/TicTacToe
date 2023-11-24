package main;

import java.awt.*;

public class Board {

    private final Rectangle[][] board;
    float dimension = (float) 400 / 3;

    public Board()
    {
        board = new Rectangle[3][3];
    }

    public void createBoard(Graphics g)
    {
        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 3; i++)
            {

                board[j][i] = new Rectangle(j * (int) dimension, i * (int) dimension, (int) dimension, (int) dimension);

                g.drawRect(board[j][i].x, board[j][i].y, (int) dimension, (int) dimension);

            }

        }

    }

    public int getNumber(int x, int y)
    {

        int number = 0;

        if(y == 0)
        {
            if(x == 133)
            {
                number = 1;
            }

            if(x == 266)
            {
                number = 2;
            }
        }

        if(y == 133)
        {
            if(x == 0)
            {
                number = 3;
            }

            if(x == 133)
            {
                number = 4;
            }

            if(x == 266)
            {
                number = 5;
            }
        }

        if(y == 266)
        {
            if(x == 0)
            {
                number = 6;
            }

            if(x == 133)
            {
                number = 7;
            }

            if(x == 266)
            {
                number = 8;
            }

        }


        return number;
    }
    public int touchBoard(int xMouse, int yMouse) {

        int number = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if(board[j][i].contains(xMouse, yMouse))
                {
                    number = getNumber(board[j][i].x, board[j][i].y);
                }

            }
        }

        return number;
    }
}
