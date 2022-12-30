/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day14;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author piko
 */
public class Day14Test {

    String testInput = """
                       498,4 -> 498,6 -> 496,6
                       503,4 -> 502,4 -> 502,9 -> 494,9""";

    public Day14Test() {
    }

    @Test
    public void fillLine() {

        var cave = new Cave(new Scanner(testInput));
        System.out.println(cave.fillLine(List.of(new Tile(10, 10), new Tile(10, 20))));
        System.out.println(cave.fillLine(List.of(new Tile(10, 10), new Tile(20, 10))));
        System.out.println(cave.fillLine(List.of(new Tile(10, 10), new Tile(10, 0))));
        System.out.println(cave.fillLine(List.of(new Tile(10, 10), new Tile(0, 10))));

    }

    @Test
    public void caveBuildTest() {
        var cave = new Cave(new Scanner(testInput));
        assertThat(cave.size()).isEqualTo(20);

    }

    @Test
    public void caveSolveTest() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result = day.getP1Answer();
        assertThat(result).isEqualTo(24);

    }

    @Test
    public void caveSolveTestp2() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result = day.getP2Answer();
        assertThat(result).isEqualTo(93);
    }

    @Test
    public void caveSolveTestBoth() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result1 = day.getP1Answer();
        var result2 = day.getP2Answer();
        assertThat(result1).isEqualTo(24);
        assertThat(result2).isEqualTo(93);
    }
    
    @Test
    public void caveSolveTestBothReversed() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result2 = day.getP2Answer();
        var result1 = day.getP1Answer();        
        
        assertThat(result2).isEqualTo(93);
        assertThat(result1).isEqualTo(24);
    }

}
