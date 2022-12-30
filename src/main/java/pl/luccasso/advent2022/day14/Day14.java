package pl.luccasso.advent2022.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

public class Day14 {

   // Map<Tile, TileType> cave;
    Set<Tile> cave;

    public Day14(Scanner scanner) {
        cave = createCave(scanner);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day14(new Scanner(new File("day14.txt"))).calculate();
    }

    private void calculate() {

    }

    Set<Tile> createCave(Scanner scanner) {
        var result = scanner.useDelimiter("\n")
                .tokens()
                .map(line -> Arrays.stream(line.split(" -> ")).map(this::processLine).toList())
                .map(this::fillLine)
                .flatMap(s->s.stream())
                .collect(Collectors.toSet());
        System.out.println(result);
        return result;
    }

    Tile processLine(String line) {

        if ("->".equals(line)) {
            return null;
        } else {
          var pair = line.split(",");
          System.out.println(pair[0] +  " : " + pair[1]);
          return new Tile(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
        }
    }

    List<Tile> fillLine(List<Tile> list){
        var result = new LinkedList<Tile>();
        var iter = list.iterator();
        var previous = iter.next();
        System.out.println("List = " + list);
        Tile next;
        while(iter.hasNext()){
            next = iter.next();
            Tile vector;
            System.out.println("Next Tile = " + next);
            if(previous.x()==next.x()){
                vector = previous.y()-next.y()<0 ? new Tile(0, 1): new Tile (0, -1);
            } else{
                vector = previous.x()-next.x()<0 ? new Tile(1,0) : new Tile (-1, 0);
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
        do{
            current = new Tile(current.x() + vector.x(), current.y() + vector.y());
            result.add(current);
        } while (!current.equals(next));
        return result;
    }
    
}
