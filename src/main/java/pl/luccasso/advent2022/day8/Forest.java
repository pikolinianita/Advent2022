/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day8;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Nauczyciel
 */
public class Forest {

    private final int[][] board;
    private int[][] visibility;

    private final int x_max;
    private final int y_max;

    public Forest(String input) {
        String[] splittedSource = input.split("\n");
        y_max = splittedSource.length;
        x_max = splittedSource[0].length();
        board = new int[x_max][y_max];
        populateArray(splittedSource);
    }

    private void populateArray(String[] source) throws NumberFormatException {
        for (int x = 0; x < x_max; x++) {
            var splittedLine = source[x].split("");
            for (int y = 0; y < y_max; y++) {
                board[x][y] = Integer.parseInt(splittedLine[y]);
            }
        }
    }

    private String print2d(int[][] source) {
        return Objects.isNull(source) ? null
                : Arrays
                        .stream(source)
                        .map(Arrays::toString)
                        .collect(Collectors.joining("\n", "\n", "\n"));
    }

    @Override
    public String toString() {
        return "Forest{" + "board:" + print2d(board) + "visible:" + print2d(visibility) + x_max + " " + y_max + '}';
    }

    public int part1() {
        visibility = new int[x_max][y_max];
        for (int i = 0; i < x_max; i++) {
            checkVisibilityCol(i);
        }
        for (int i = 0; i < y_max; i++) {
            checkVisibilityRow(i);
        }
        return countVisible();
    }

    private void checkVisibilityCol(int col) {
        fromUp(col);
        fromDown(col);
    }

    private void fromDown(int col) {
        int max_down = -1;
        for (int i = y_max - 1; i > -1; i--) {
            if (board[i][col] > max_down) {
                visibility[i][col] = 1;
                max_down = board[i][col];
            }
        }
    }

    private void fromUp(int col) {
        int max_up = -1;
        for (int i = 0; i < y_max; i++) {
            if (board[i][col] > max_up) {
                visibility[i][col] = 1;
                max_up = board[i][col];
            }
        }
    }

    private void checkVisibilityRow(int row) {
        fromLeft(row);
        fromRight(row);
    }

    private void fromRight(int row) {
        int max_right = -1;
        for (int i = x_max - 1; i > -1; i--) {
            if (board[row][i] > max_right) {
                visibility[row][i] = 1;
                max_right = board[row][i];
            }
        }
    }

    private void fromLeft(int row) {
        int max_left = -1;
        for (int i = 0; i < x_max; i++) {
            if (board[row][i] > max_left) {
                visibility[row][i] = 1;
                max_left = board[row][i];
            }
        }
    }

    public int part2() {
        visibility = new int[x_max][x_max];
        for (int x = 1; x < x_max - 1; x++) {
            for (int y = 1; y < y_max - 1; y++) {
                visibility[x][y] = calculateScenic(x, y);
            }
        }
        return maxScenic();
    }

    private int countVisible() {
        return Arrays.stream(visibility)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    private int maxScenic() {
        return Arrays.stream(visibility)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElseThrow();
    }

    private int calculateScenic(int x, int y) {
        return calcLine(board[x][y], x, y, 1, 0)
                * calcLine(board[x][y], x, y, -1, 0)
                * calcLine(board[x][y], x, y, 0, 1)
                * calcLine(board[x][y], x, y, 0, -1);
    }

    private int calcLine(int h, int x, int y, int dx, int dy) {
        if (outOfForest(x + dx, y + dy) || tooHigh(h, x + dx, y + dy)) {
            return 1;
        } else {
            return 1 + calcLine(h, x + dx, y + dy, dx, dy);
        }
    }

    private boolean outOfForest(int x, int y) {
        return (x == 0) || (y == 0) || (x == x_max - 1) || y == (y_max - 1);
    }

    private boolean tooHigh(int h, int x, int y) {
        return h <= board[x][y];
    }

}
