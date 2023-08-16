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

    //Position Head;

    //Position Tail;
    
    Position[] bridge;

    Set<Position> occupiedByTail;

    List<Command> commandLines;
    
    int size;

    public Bridge(List<String> commands, int size) {
        var start = new Position(0, 0);
        bridge = new Position[size];
        for (int i = 0; i<size; i++){
            bridge[i] = start;
        }
        occupiedByTail = new HashSet<>();
        occupiedByTail.add(start);
        commandLines = commands.stream()
                .map(this::parseLine)
                .toList();
        this.size = size;
    }

    private Command parseLine(String line) {
        var arr = line.split(" ");
        return new Command(Direction.valueOf(arr[0]), Integer.valueOf(arr[1]));
    }

    int part1() {
        doTurns();
        return occupiedByTail.size();
    }
    
    int part2(){
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
        bridge[0] = new Position(
                bridge[0].x() + dir.dx(),
                bridge[0].y() + dir.dy());
    }

    private void adjustTail(){
        for (int i =1; i<size; i++){
            bridge[i] = adjustTile(bridge[i-1],bridge[i]);
        }
        occupiedByTail.add(bridge[size-1]);
    }
    
    private Position adjustTile(Position Head, Position Tail) {
        var dx = Head.x() - Tail.x();
        var dy = Head.y() - Tail.y();
        if ((abs(dx) < 2) && (abs(dy) < 2)) {
            return Tail;
        } else if (dx == 0) {
            return new Position(Tail.x(), Tail.y() + signum(dy));
        } else if (dy == 0) {
            return  new Position(Tail.x() + signum(dx), Tail.y());
        } else {
            return new Position(Tail.x() + signum(dx), Tail.y() + signum(dy));
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        var input = new Scanner(new File("day09.txt"))
                .useDelimiter("\n")
                .tokens()
                .toList();
        System.out.println(input);
        
        var result = new Bridge(input, 2).part1();
        System.out.println("result for p1 is: " + result);
        result = new Bridge(input, 10).part1();
        System.out.println("result for p2 is: " + result);
        
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
