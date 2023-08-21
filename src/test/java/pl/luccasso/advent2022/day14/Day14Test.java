/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day14;

import static org.assertj.core.api.Assertions.*;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
class Day14Test {

    String testInput = """
                       498,4 -> 498,6 -> 496,6
                       503,4 -> 502,4 -> 502,9 -> 494,9""";
    
    @Test
    void caveBuildTest() {
        var cave = new Cave(new Scanner(testInput));
        assertThat(cave.size()).isEqualTo(20);

    }

    @Test
    void caveSolveTest() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result = day.getP1Answer();
        
        assertThat(result).isEqualTo(24);
    }

    @Test
    void caveSolveTestp2() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result = day.getP2Answer();
        
        assertThat(result).isEqualTo(93);
    }

    @Test
    void caveSolveTestBoth() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result1 = day.getP1Answer();
        var result2 = day.getP2Answer();
        
        assertThat(result1).isEqualTo(24);
        assertThat(result2).isEqualTo(93);
    }
    
    @Test
    void caveSolveTestBothReversed() {
        var cave = new Cave(new Scanner(testInput));
        var day = new Day14(cave);
        var result2 = day.getP2Answer();
        var result1 = day.getP1Answer();        
        
        assertThat(result2).isEqualTo(93);
        assertThat(result1).isEqualTo(24);
    }

}
