package pl.luccasso.advent2022.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author piko
 */
enum TileType {
    SAND, ROCK
}

record Tile(int x, int y) {

}

final class Cave {

    Set<Tile> cave;

    int originalSize;

    int maxDepth;

    public Cave(Scanner scanner) {
        cave = createCave(scanner);
        originalSize = cave.size();
        maxDepth = cave.stream().mapToInt(Tile::y).max().getAsInt();
    }

    final Set<Tile> createCave(Scanner scanner) {
        var result = scanner.useDelimiter("\n")
                .tokens()
                .map(line -> Arrays.stream(line.split(" -> ")).map(this::processLine).toList())
                .map(this::fillLine)
                .flatMap(s -> s.stream())
                .collect(Collectors.toSet());
        return result;
    }

    Tile processLine(String line) {

        if ("->".equals(line)) {
            return null;
        } else {
            var pair = line.split(",");
            return new Tile(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
        }
    }

    List<Tile> fillLine(List<Tile> list) {
        var result = new LinkedList<Tile>();
        var iter = list.iterator();
        var previous = iter.next();
        Tile next;
        while (iter.hasNext()) {
            next = iter.next();
            Tile vector;
            if (previous.x() == next.x()) {
                vector = previous.y() - next.y() < 0 ? new Tile(0, 1) : new Tile(0, -1);
            } else {
                vector = previous.x() - next.x() < 0 ? new Tile(1, 0) : new Tile(-1, 0);
            }

            result.addAll(fillFragment(previous, next, vector));
            previous = next;
        }
        return result;
    }

    private List<Tile> fillFragment(Tile previous, Tile next, Tile vector) {
        var result = new ArrayList<Tile>();
        result.add(previous);
        Tile current = previous;
        do {
            current = new Tile(current.x() + vector.x(), current.y() + vector.y());
            result.add(current);
        } while (!current.equals(next));
        return result;
    }

    public boolean exist(Tile tile) {
        return cave.contains(tile);
    }

    public boolean exist(int x, int y) {
        return cave.contains(new Tile(x, y));
    }

    public Cave add(Tile tile) {
        cave.add(tile);
        return this;
    }

    public int size() {
        return cave.size();
    }

    public int getSand() {
        return cave.size() - originalSize;
    }

    public int getMaxDeepth() {
        return maxDepth;
    }

}

public class Day14 {

    Cave cave;
    private final Tile start;
    private boolean finishedP1;

    public Day14(Cave cave) {
        this.cave = cave;
        start = new Tile(500, 0);
        finishedP1 = false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        var cave = new Cave(new Scanner(new File("day14.txt")));
        new Day14(cave).calculate();
    }

    private void calculate() {
       // System.out.println("Answer part 1 is: " + getP1Answer());
        System.out.println("Answer part 2 is: " + getP2Answer());
    }

    int getP1Answer() {
        drop(start);
        return cave.getSand();
    }
    
    int getP2Answer() {
        dropP2(start);
        return cave.getSand();
    }

    void drop(Tile start) {
        if (start.y() > cave.maxDepth || finishedP1) {
            finishedP1 = true;
            return;
        }
        if (!cave.exist(start.x(), start.y() + 1)) {
            drop(new Tile(start.x(), start.y() + 1));
        }
        if (!cave.exist(start.x() - 1, start.y() + 1)) {
            drop(new Tile(start.x() - 1, start.y() + 1));
        }
        if (!cave.exist(start.x() + 1, start.y() + 1)) {
            drop(new Tile(start.x() + 1, start.y() + 1));
        }

        if (!finishedP1) {
            cave.add(start);
        }
    }

    void dropP2(Tile start) {
        
        if (start.y() > cave.maxDepth + 1) {
            // finishedP2 = true;
            return;
        }
        if (!cave.exist(start.x(), start.y() + 1)) {
            dropP2(new Tile(start.x(), start.y() + 1));
        }
        if (!cave.exist(start.x() - 1, start.y() + 1)) {
            dropP2(new Tile(start.x() - 1, start.y() + 1));
        }
        if (!cave.exist(start.x() + 1, start.y() + 1)) {
            dropP2(new Tile(start.x() + 1, start.y() + 1));
        }

        cave.add(start);
       
    }

}
