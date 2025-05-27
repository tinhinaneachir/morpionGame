package morpion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MorpionSwing extends JFrame {
    private JButton[] buttons = new JButton[9];
    private char currentPlayer = 'X';
    private boolean gameEnded = false;

    public MorpionSwing() {
        setTitle("Jeu du Morpion");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonListener(i));
            add(buttons[i]);
        }

        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        private int index;
        public ButtonListener(int i) {
            this.index = i;
        }

        public void actionPerformed(ActionEvent e) {
            if (gameEnded) return;
            JButton button = buttons[index];
            if (!button.getText().equals("")) return;

            button.setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                JOptionPane.showMessageDialog(null, "Le joueur " + currentPlayer + " a gagné !");
                gameEnded = true;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "Match nul !");
                gameEnded = true;
            } else {
                togglePlayer();
            }
        }
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWin() {
        // lignes
        for (int i = 0; i <= 6; i += 3) {
            if (checkLine(i, i + 1, i + 2)) return true;
        }
        // colonnes
        for (int i = 0; i < 3; i++) {
            if (checkLine(i, i + 3, i + 6)) return true;
        }
        // diagonales
        if (checkLine(0, 4, 8)) return true;
        if (checkLine(2, 4, 6)) return true;

        return false;
    }

    private boolean checkLine(int i1, int i2, int i3) {
        String s1 = buttons[i1].getText();
        String s2 = buttons[i2].getText();
        String s3 = buttons[i3].getText();
        return !s1.equals("") && s1.equals(s2) && s2.equals(s3);
    }

    private boolean isBoardFull() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MorpionSwing::new);
    }
}
