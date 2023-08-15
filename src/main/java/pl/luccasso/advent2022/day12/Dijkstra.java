/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Teresa
 */
public class Dijkstra implements Solver {

    Map<Position, Integer> visited;
    Queue<Position> toVisit;

    Position start;
    Position end;
    List<List<Integer>> board;
    private int maxY;
    private int maxX;
    private int turn;
    private final PartStrategy strategy;

    public static Dijkstra withStrategyPart(int n){
        return new Dijkstra(n);
    }
    
    private Dijkstra(int n){
        if (n == 1) 
            strategy = new Part1Strategy();
        else
            strategy = new Part2Strategy();
    }
    
    @Override
    public int solve(Position start, Position end, List<List<Integer>> board) {
        System.out.println("solve");
        this.start = start;
        this.end = end;
        this.board = board;
        turn = 0;
        maxY = board.size();
        maxX = board.get(0).size();
        visited = new HashMap<>();
        toVisit = new LinkedList<>();
        strategy.qeueuSetup();
       // visited.put(this.start, 0);
       // toVisit.offer(start);
        return doLoop();

    }

    int doLoop() {
        while (!toVisit.isEmpty()) {
            turn++;
            var next = toVisit.poll();
            if (strategy.hasEnded(next)) {
                return visited.get(next);
            }
            pushNextTiles(next);
        }
        return 0;
    }

    private void pushNextTiles(Position current) {
        System.out.println("processing: " + current);
        var dist = visited.get(current);
        Stream.of(new Position(current.x() + 1, current.y()),
                new Position(current.x() - 1, current.y()),
                new Position(current.x(), current.y() + 1),
                new Position(current.x(), current.y() - 1))
                .filter(this::isOnBoard)
                .filter(this::isUnvisited)
                .peek(System.out::println)
                .filter(next -> strategy.isReachable(current, next))
                .forEach(next -> push(next, dist + 1));

    }

    boolean isOnBoard(Position pos) {
        return (pos.x() > -1) && (pos.y() > -1) && (pos.x() < maxX) && (pos.y() < maxY);
    }

    boolean isUnvisited(Position pos) {
        return !visited.containsKey(pos);
    }

    boolean isReachable(Position current, Position next) {
        var result = (getValue(next) - getValue(current)) < 2;
        return result;
    }

    void push(Position pos, int distance) {
        toVisit.offer(pos);
        visited.put(pos, distance);
    }

    int getValue(Position pos) {
        return board.get(pos.y()).get(pos.x());
    }

    public class Part1Strategy implements PartStrategy {

        @Override
        public void qeueuSetup() {
            visited.put(start, 0);
            toVisit.offer(start);
        }

        @Override
        public boolean hasEnded(Position pos) {
            System.out.println(pos);
            return pos.equals(end);
        }

        @Override
        public boolean isReachable(Position current, Position next) {
           return (getValue(next) - getValue(current)) < 2;
        }

    }
    
    public class Part2Strategy implements PartStrategy {

        @Override
        public void qeueuSetup() {
            visited.put(end, 0);
            toVisit.offer(end);
        }

        @Override
        public boolean hasEnded(Position pos) {
           return getValue(pos) == 0;
        }

        @Override
        public boolean isReachable(Position current, Position next) {
           return (getValue(current) - getValue(next)) < 2;
        }

    }
}
