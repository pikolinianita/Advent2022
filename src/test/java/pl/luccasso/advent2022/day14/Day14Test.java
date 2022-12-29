/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day14;

import java.util.Arrays;
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
    public void testSomeMethod() {
        var sc = new Scanner(testInput);
        var sc2 = new Scanner(testInput);
       // System.out.println(new Day14(sc).createCave(sc));
        var day = new Day14(sc);
      //System.out.println(Arrays.toString(testInput.split("[ ,/-/>]")));
      //sc.tokens().toList().forEach(System.out::println);
      sc2.tokens()
              .toList()
              .stream()
              .peek(l-> System.out.println("this is line : " + l))
              .map(day::processLine)
              .filter(Objects::nonNull)
              .peek(l-> System.out.println("this is sec line : " + l))
              .toList();
    }
    
    @Test
    public void anotheTry() {
         var day = new Day14(new Scanner(testInput));
      testInput.lines()
              .map(line-> Arrays.stream(line.split(" -> "))
                      .map(day::processLine)
                      .toList())
              .peek(System.out::println)
              .toList();
        
    }
    
    @Test
    public void onemore() {
        var sc = new Scanner(testInput).useDelimiter("\n");
        sc.tokens().peek(l -> System.out.println("here " + l)).toList();
       
    }
    
    @Test
    public void JustCave() {
         var sc = new Scanner(testInput);
         System.out.println(new Day14(sc).createCave(sc));
        
    }
}
