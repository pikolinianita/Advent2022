/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.luccasso.advent2022.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 *
 * @author Teresa
 */
public class Pathfinding {

    final int CODE_POINT_a = 97;
    final int VALUE_S = -14;
    final int VALUE_E = -28;
    
    final List<List<Integer>> board;
    
    final Position start;
    
    final Position end;
    
    private final Solver solver;

    public static void main(String[] args) {
        System.out.println("Hello World!");      
    }
   
    public Pathfinding(String input, int part) {        
        board = processInputString(input);
        start = findPositionAndReplace(VALUE_S,0);
        end = findPositionAndReplace(VALUE_E,25);
        solver = Dijkstra.withStrategyPart(part);
    }

    private List<List<Integer>> processInputString(String input) {
       return Arrays.stream(input.split("\n"))
                .map(this::processLine)
                .collect(toCollection(ArrayList::new));
    }

    private  List<Integer> processLine(String str) {
        return Arrays.stream(str.split(""))
                .map(ch -> ch.codePointAt(0)-CODE_POINT_a)
                .collect(toCollection(ArrayList::new));
    }
    
    public int solve() {
       return solver.solve(start, end, board);
    }

    private Position findPositionAndReplace(int value, int replacement) {
       for (int i =0; i< board.size(); i++){
           var targetPosition = board.get(i).indexOf(value);
           if (targetPosition >-1){
               board.get(i).set(targetPosition, replacement);
               return new Position(targetPosition, i);
           }
       }
       throw new IllegalArgumentException("wrong value: " + value);
    }

    @Override
    public String toString() {
        return "Pathfinding{" + "board=" + board + ", start=" + start + ", end=" + end + '}';
    }

}

