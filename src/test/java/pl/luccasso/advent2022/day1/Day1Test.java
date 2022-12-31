/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day1;

import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
public class Day1Test {
    
    String inp = """
                 1000
                 2000
                 3000
                 
                 4000
                 
                 5000
                 6000
                 
                 7000
                 8000
                 9000
                 
                 10000""";
    
    public Day1Test() {
    }

    @Test
    public void testDay1p1() throws FileNotFoundException {
        
        var result = new Day1("").calculateP1(new Scanner(inp).useDelimiter("\n"));
 
        assertThat(result).as("part 1").isEqualTo(24000L);
    }
    
    @Test
    public void testDay1p2() throws FileNotFoundException {
        
        var result = new Day1("").calculateP2(new Scanner(inp).useDelimiter("\n"));
 
        assertThat(result).as("part 2").isEqualTo(45000L);
    }
    
}
