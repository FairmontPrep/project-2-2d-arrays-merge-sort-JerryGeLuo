import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array for board
    private String[][] piecesArray; // 2D array = image::HP::board position

    public GameBoard() {
        setTitle("Poke Board");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Use GridLayout for the board layout

        // Initialize the 2D array of panels
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(255, 251, 240)); // Light squares
                } else {
                    squares[row][col].setBackground(Color.PINK); // Dark squares
                }
                add(squares[row][col]); // Add each square to the board
            }
        }

        // Initialize Pokémon pieces array
        piecesArray = new String[32][3]; // Pokémon pieces: image, HP, board position
        loadPieces();

        // Sort using Merge Sort for extra credit
        mergeSort(piecesArray, 0, piecesArray.length - 1);

        // Print sorted pieces array for verification
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        // Initially populate the board with pieces
        populateBoard();
    }

    private void mergeSort(String[][] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private void merge(String[][] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[][] leftArray = new String[n1][3];
        String[][] rightArray = new String[n2][3];

        for (int i = 0; i < n1; i++)
            leftArray[i] = array[left + i];

        for (int i = 0; i < n2; i++)
            rightArray[i] = array[mid + 1 + i];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (Integer.parseInt(leftArray[i][2]) <= Integer.parseInt(rightArray[j][2])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private void populateBoard() {
        int pieceRow = 0; // Keeps track of the pieces being placed
        int squareName = 0; // Tracks board positions 1-64

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pieceRow < piecesArray.length) {
                    int pokePosition = Integer.parseInt(piecesArray[pieceRow][2]);

                    if (squareName == pokePosition) {
                        String imagePath = piecesArray[pieceRow][0];
                        String hpText = piecesArray[pieceRow][1];

                        ImageIcon icon = new ImageIcon(imagePath);
                        Image scaledImage = icon.getImage().getScaledInstance(40, 42, Image.SCALE_SMOOTH);

                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                        JLabel textLabel = new JLabel(hpText, SwingConstants.CENTER);
                        textLabel.setForeground(Color.BLACK);

                        JPanel piecePanel = new JPanel(new BorderLayout());
                        piecePanel.setOpaque(false);
                        piecePanel.add(pieceLabel, BorderLayout.CENTER);
                        piecePanel.add(textLabel, BorderLayout.SOUTH);

                        squares[row][col].setLayout(new BorderLayout());
                        squares[row][col].add(piecePanel, BorderLayout.CENTER);

                        System.out.println("Adding piece: " + hpText);

                        pieceRow++;
                    }
                }
                squareName++;
            }
        }

        revalidate();
        repaint();
    }

    private void loadPieces() {
        piecesArray[0] = new String[]{"squirtle.png", "HP:200", "1"};
        piecesArray[1] = new String[]{"squirtle.png", "HP:201", "9"};
        piecesArray[2] = new String[]{"squirtle.png", "HP:202", "17"};
        piecesArray[3] = new String[]{"squirtle.png", "HP:203", "25"};
        piecesArray[4] = new String[]{"squirtle.png", "HP:32", "33"};
        piecesArray[5] = new String[]{"squirtle.png", "HP:31", "41"};
        piecesArray[6] = new String[]{"eevee.png", "HP:172", "2"};
        piecesArray[7] = new String[]{"vaporeon.png", "HP:100", "3"};
        piecesArray[8] = new String[]{"espeon.png", "HP:104", "11"};
        piecesArray[9] = new String[]{"umbreon.png", "HP:123", "19"};
        piecesArray[10] = new String[]{"jolteon.png", "HP:134", "27"};
        piecesArray[11] = new String[]{"flareon.png", "HP:155", "36"};
        piecesArray[12] = new String[]{"leafeon.png", "HP:182", "49"};
        piecesArray[13] = new String[]{"sylveon.png", "HP:111", "54"};
        piecesArray[14] = new String[]{"glaceon.png", "HP:300", "62"};
        piecesArray[15] = new String[]{"ice-stone.png", "HP:1", "63"};
        piecesArray[16] = new String[]{"water-stone.png", "HP:2", "4"};
        piecesArray[17] = new String[]{"fire-stone.png", "HP:3", "37"};
        piecesArray[18] = new String[]{"thunder-stone.png", "HP:4", "28"};
        piecesArray[19] = new String[]{"shiny-stone.png", "HP:5", "55"};
        piecesArray[20] = new String[]{"dawn-stone.png", "HP:6", "12"};
        piecesArray[21] = new String[]{"dusk-stone.png", "HP:7", "20"};
        piecesArray[22] = new String[]{"leaf-stone.png", "HP:8", "50"};
        piecesArray[23] = new String[]{"cool.png", "HP:9", "10"};
        piecesArray[24] = new String[]{"wartortle.png", "HP:215", "8"};
        piecesArray[25] = new String[]{"wartortle.png", "HP:189", "16"};
        piecesArray[26] = new String[]{"wartortle.png", "HP:202", "24"};
        piecesArray[27] = new String[]{"wartortle.png", "HP:178", "32"};
        piecesArray[28] = new String[]{"wartortle.png", "HP:220", "40"};
        piecesArray[29] = new String[]{"wartortle.png", "HP:195", "48"};
        piecesArray[30] = new String[]{"pokeball.png", "HP:255", "52"};
        piecesArray[31] = new String[]{"pokeball.png", "HP:265", "51"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}

