import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    // creating window
    JFrame frame = new JFrame("Tic-Tac-Toe");
    // add panel
    JLabel textJLabel = new JLabel();
    JPanel textJPanel = new JPanel();
    // add board
    JPanel boardPanel = new JPanel();
    // button for tictactoe
    JButton[][] boardButtons = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String fontType = "Calibri";

    String currentPlay = playerX;
    Boolean gameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        // open window at center
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textJLabel.setBackground(Color.darkGray);
        textJLabel.setForeground(Color.white);
        textJLabel.setFont(new Font(fontType,
                Font.BOLD, 50));
        textJLabel.setHorizontalAlignment(JLabel.CENTER);
        textJLabel.setText("Tic -Tac-Toe");
        textJLabel.setOpaque(true);

        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textJLabel);
        frame.add(textJPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.gray);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                boardButtons[i][j] = tile;
                boardPanel.add(tile);

                tile.setForeground(Color.gray);
                tile.setFont(new Font(fontType,
                        Font.BOLD, 120));
                tile.setFocusable(false);

                // for each tile need to have action listener
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JButton tile = (JButton) event.getSource();
                        if (gameOver) {
                            // textJLabel.setText("Game Over");
                            return;

                        }
                        // add check allow set text only if empty
                        if (tile.getText().isEmpty()) {
                            tile.setText(currentPlay);
                            checkWinner();
                            // alternate Player
                            if (!gameOver) {
                                if (currentPlay == playerX) {
                                    currentPlay = playerO;
                                } else {
                                    currentPlay = playerX;
                                }
                                textJLabel.setText(currentPlay + "'s turn.");
                            }

                        }

                    }
                });
            }
        }
    }

    void checkWinner() {
        // check horizontal
        for (int i = 0; i < 3; i++) {
            if (!boardButtons[i][0].getText().isEmpty()) {
                if (boardButtons[i][0].getText() == boardButtons[i][1].getText() &&
                        boardButtons[i][0].getText() == boardButtons[i][2].getText()) {
                    for (int j = 0; j < 3; j++) {
                        setWinner(boardButtons[i][j]);
                    }
                    gameOver = true;
                    return;
                }
            } else {
                continue;
            }
        }
        // check vertical
        for (int j = 0; j < 3; j++) {
            if (!boardButtons[0][j].getText().isEmpty()) {
                if (boardButtons[0][j].getText() == boardButtons[1][j].getText() &&
                        boardButtons[0][j].getText() == boardButtons[2][j].getText()) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(boardButtons[i][j]);
                    }
                    gameOver = true;
                    return;
                }
            } else {
                continue;
            }
        }
        // check diagonal
        if (!boardButtons[1][1].getText().isEmpty()) {
            if ((boardButtons[0][0].getText() == boardButtons[1][1].getText() &&
                    boardButtons[2][2].getText() == boardButtons[0][0].getText()) ||
                    (boardButtons[0][2].getText() == boardButtons[1][1].getText() &&
                            boardButtons[2][0].getText() == boardButtons[0][2].getText())) {
                gameOver = true;
                textJLabel.setText("Game Over");

                return;
            }
        }

    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        textJLabel.setText(currentPlay + " is the Winner");
    }
}
