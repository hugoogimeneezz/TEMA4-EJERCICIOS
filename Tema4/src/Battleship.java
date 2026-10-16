import java.util.Scanner;
public class Battleship {

    public static char EMPTY = '·';
    public static char SHIP = 'S';
    public static char SUNK_SHIP = 'X';
    public static char WATER = 'O';

    public static char NUM_SHIPS = 10;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[][] matrix = new char[8][8];
        boolean gameOver = false;
        int shipsSunk = 0;

        // Inicializar el tablero
        for (int fila = 0; fila < matrix.length; fila++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[fila][col] = EMPTY;
            }
        }

        addShips(matrix);

        System.out.println("=== HUNDIR LA FLOTA ===");
        System.out.println("Encuentra los 10 barcos escondidos.");
        System.out.println("Las X son barcos tocados y las O son agua.");

        while (!gameOver) {
            printMatrix(matrix, false);

            int row = getRow(input);
            int col = getCol(input);

            // Comprobar que las coordenadas sean válidas
            if (row < 0 || row >= matrix.length ||
                    col < 0 || col >= matrix[0].length) {
                System.out.println("Coordenadas no validas. Intentalo de nuevo.");
                continue;
            }

            // Comprobar si ya se ha disparado en esa casilla
            if (matrix[row][col] == SUNK_SHIP || matrix[row][col] == WATER) {
                System.out.println("Ya has disparado en esa casilla.");
                continue;
            }

            // Comprobar si el disparo acierta
            if (matrix[row][col] == SHIP) {
                matrix[row][col] = SUNK_SHIP;
                shipsSunk++;
                System.out.println("¡TOCADO!");
                System.out.println("Barcos encontrados: " + shipsSunk + "/10");
            } else {
                matrix[row][col] = WATER;
                System.out.println("¡AGUA!");
            }

            // Comprobar si se han encontrado todos los barcos
            if (shipsSunk == NUM_SHIPS) {
                gameOver = true;
            }
        }

        // Final de la partida
        printMatrix(matrix, true);
        System.out.println("¡FELICIDADES! ¡HAS GANADO!");
        input.close();
    }

    static void addShips(char[][] tabla) {
        int numShips = 0;

        while (numShips < NUM_SHIPS) {
            int row = (int) (Math.random() * tabla.length);
            int col = (int) (Math.random() * tabla[0].length);

            if (tabla[row][col] != SHIP) {
                tabla[row][col] = SHIP;
                numShips++;
            }
        }
    }

    // Si debug es true, muestra tambien los barcos.
    static void printMatrix(char[][] tabla, boolean debug) {
        System.out.print("  ");

        for (int i = 1; i <= 8; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int row = 0; row < tabla.length; row++) {
            System.out.print((char) (65 + row) + " ");

            for (int col = 0; col < tabla[0].length; col++) {
                if (tabla[row][col] == SHIP && !debug) {
                    System.out.print(EMPTY + " ");
                } else {
                    System.out.print(tabla[row][col] + " ");
                }
            }

            System.out.println();
        }
    }

    static int getRow(Scanner input) {
        System.out.print("Dame la letra de la fila: ");
        char letter = input.next().toUpperCase().charAt(0);
        return letter - 'A';
    }

    static int getCol(Scanner input) {
        System.out.print("Dime el numero de columna: ");
        return input.nextInt() - 1;
    }
}