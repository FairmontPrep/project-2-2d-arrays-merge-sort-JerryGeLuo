import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GameBoard extends JFrame {
    private static final int GRID_ROWS = 18; // Adjust based on image
    private static final int GRID_COLS = 18;
    private JPanel[][] squares = new JPanel[GRID_ROWS][GRID_COLS];
    private int[][] imagePixels; // Stores pixel color data

    public GameBoard(String imagePath) {
        setTitle("Fried Egg Pixel Art");
        setSize(GRID_COLS * 30, GRID_ROWS * 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        // Load the image and convert it to a 2D array
        loadImage(imagePath);

        // Sort the image pixels before displaying
        sortImagePixels();

        // Initialize the board with sorted image colors
        initializeBoard();

        setVisible(true);
    }

    private void loadImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int width = image.getWidth();
            int height = image.getHeight();
            imagePixels = new int[height][width];

            // Extract pixel colors
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    imagePixels[y][x] = image.getRGB(x, y);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortImagePixels() {
        for (int row = 0; row < imagePixels.length; row++) {
            MergeSort.mergeSort(imagePixels, row, 0, imagePixels[0].length - 1);
        }
    }

    private void initializeBoard() {
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                squares[row][col] = new JPanel();
                if (row < imagePixels.length && col < imagePixels[0].length) {
                    squares[row][col].setBackground(new Color(imagePixels[row][col]));
                } else {
                    squares[row][col].setBackground(Color.BLACK); // Default empty color
                }
                add(squares[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        new GameBoard("fried_egg.png"); // Replace with the correct image filename
    }
}

// MergeSort class for sorting image pixels
class MergeSort {
    public static void mergeSort(int[][] array, int row, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, row, left, mid);
            mergeSort(array, row, mid + 1, right);
            merge(array, row, left, mid, right);
        }
    }

    private static void merge(int[][] array, int row, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[row][left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[row][mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (getBrightness(leftArray[i]) <= getBrightness(rightArray[j])) {
                array[row][k] = leftArray[i];
                i++;
            } else {
                array[row][k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[row][k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[row][k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static int getBrightness(int color) {
        Color c = new Color(color);
        return (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
    }
}
