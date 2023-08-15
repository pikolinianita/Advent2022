/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
public class Day4Test {
    
    String input = """
                   2-4,6-8
                   2-3,4-5
                   5-7,7-9
                   2-8,3-7
                   6-6,4-6
                   2-6,4-8""";
    
    public Day4Test() {
    }

    @Test
    public void testShuouldBeFalse() {       
        boolean result = new Day4("_").needReconsideration("2-3,4-5");
        assertThat(result).isFalse();
    }
    
    @Test
    public void testshouldBeTrue() {       
        boolean result = new Day4("_").needReconsideration("2-8,3-7");
        assertThat(result).isTrue();
    }
    
      @Test
    public void testPart1() {       
        long result = new Day4("_").solvePart1(input.lines().toList());
        assertThat(result).isEqualTo(2);
    }
    
    @Test
    public void testPart2() {       
        long result = new Day4("_").solvePart2(input.lines().toList());
        assertThat(result).isEqualTo(4);
    }
    
    @Test
    public void testFail() {       
        boolean result = new Day4("_").needReconsideration("16-80,80-87");
        assertThat(result).isFalse();
    }
    
}
