/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day12;

import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
public class BoardTest {
    
    String test = """
                  Sabqponm
                  abcryxxl
                  accszExk
                  acctuvwj
                  abdefghi""";

    @Test
    public void testMakeBoard() {
        
       var result = Board.makeBoard(new Scanner(test).tokens().toList());
        System.out.println( result.toString());
        System.out.println(Character.toChars(result.get(0, 0)));
        System.out.println(Character.toChars(result.get(1, 0)));
        System.out.println(Character.toChars(result.get(2, 0)));
        System.out.println(Character.toChars(result.get(0, 1)));
        System.out.println(Character.toChars(result.get(0, 2)));
        System.out.println(result.get(0, 8));
        System.out.println(result.get(5, 0));
        
        assertThat(result.get(0, 0)).isEqualTo(83);
        assertThat(result.get(1, 0)).isEqualTo(97);
        assertThat(result.get(5, 2)).isEqualTo(83);
        assertThat(result.get(0, 8)).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.get(5, 0)).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.getStart()).isEqualTo(new Point(0, 0));
        assertThat(result.getEnd()).isEqualTo(new Point(7, 8));
                
        
    }

    @Test
    public void testGet() {
    }
    
}
