/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day9;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.signum;
import static java.lang.Math.abs;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Nauczyciel
 */
public class Bridge {

    Position Head;

    Position Tail;

    Set<Position> occupiedByTail;

    List<Command> commandLines;

    public Bridge(List<String> commands) {
        Head = new Position(0, 0);
        Tail = new Position(0, 0);
        occupiedByTail = new HashSet<>();
        occupiedByTail.add(Tail);
        commandLines = commands.stream()
                .map(this::parseLine)
                .toList();
    }

    private Command parseLine(String line) {
        var arr = line.split(" ");
        return new Command(Direction.valueOf(arr[0]), Integer.valueOf(arr[1]));
    }

    int part1() {
        doTurns();
        return occupiedByTail.size();
    }

    private void doTurns() {
        commandLines.forEach(command -> {
            for (int i = 0; i < command.amount(); i++) {
                moveHead(command.dir());
                adjustTail();
            }
        });
    }

    private void moveHead(Direction dir) {
        Head = new Position(
                Head.x() + dir.dx(),
                Head.y() + dir.dy());
    }

    private void adjustTail() {
        var dx = Head.x() - Tail.x();
        var dy = Head.y() - Tail.y();
        if ((abs(dx) < 2) && (abs(dy) < 2)) {
            return;
        } else if (dx == 0) {
            Tail = new Position(Tail.x(), Tail.y() + signum(dy));
        } else if (dy == 0) {
            Tail = new Position(Tail.x() + signum(dx), Tail.y());
        } else {
            Tail = new Position(Tail.x() + signum(dx), Tail.y() + signum(dy));
        }
        occupiedByTail.add(Tail);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        var input = new Scanner(new File("day09.txt"))
                .useDelimiter("\n")
                .tokens()
                .toList();
        System.out.println(input);
        
        var result = new Bridge(input).part1();
        System.out.println("result for p1 is: " + result);
    }

}

enum Direction {
    L(-1, 0),
    R(1, 0),
    U(0, 1),
    D(0, -1);

    final int dx;
    final int dy;

    Direction(int x, int y) {
        dx = x;
        dy = y;
    }

    int dx() {
        return dx;
    }

    int dy() {
        return dy;
    }
}

record Position(int x, int y) {

}

record Command(Direction dir, int amount) {

};
