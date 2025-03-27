import java.util.Scanner;

public class Sudoku {

    // Tabuleiro estático de Sudoku - 0 representa espaços vazios
    private static int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard(); // Exibir o tabuleiro atual
            System.out.println("Digite sua jogada no formato 'linha coluna valor' (ou 'sair' para encerrar):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Entrada inválida! Use o formato 'linha coluna valor'.");
                continue;
            }

            try {
                int linha = Integer.parseInt(parts[0]) - 1;
                int coluna = Integer.parseInt(parts[1]) - 1;
                int valor = Integer.parseInt(parts[2]);

                if (isValidMove(linha, coluna, valor)) {
                    board[linha][coluna] = valor;
                } else {
                    System.out.println("Movimento inválido. Tente novamente.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Entrada inválida! Certifique-se de usar números válidos.");
            }
        }

        scanner.close();
    }

    // Função para imprimir o tabuleiro do Sudoku
    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                if (board[i][j] == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Função para validar movimentos
    private static boolean isValidMove(int linha, int coluna, int valor) {
        if (valor < 1 || valor > 9 || board[linha][coluna] != 0) {
            return false;
        }

        // Verificar a linha
        for (int i = 0; i < 9; i++) {
            if (board[linha][i] == valor) {
                return false;
            }
        }

        // Verificar a coluna
        for (int i = 0; i < 9; i++) {
            if (board[i][coluna] == valor) {
                return false;
            }
        }

        // Verificar a subgrade 3x3
        int startRow = (linha / 3) * 3;
        int startCol = (coluna / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == valor) {
                    return false;
                }
            }
        }

        return true;
    }
}
