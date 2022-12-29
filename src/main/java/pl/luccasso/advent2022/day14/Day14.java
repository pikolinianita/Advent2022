package pl.luccasso.advent2022.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    Map<Tile, TileType> cave;

    public Day14(Scanner scanner) {
        cave = createCave(scanner);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day14(new Scanner(new File("day14.txt"))).calculate();
    }

    private void calculate() {

    }

    Map<Tile, TileType> createCave(Scanner scanner) {
        var result = scanner.useDelimiter("\n")
                .tokens()
                .map(line -> Arrays.stream(line.split(" -> ")).map(this::processLine).toList())
                .map(this::fillLine)
                .toList();
        System.out.println(result);
        return null;
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
        var iter = list.iterator();
        var previous = iter.next();
        while(iter.hasNext()){
            
        }
        return null;
    }
    
}
