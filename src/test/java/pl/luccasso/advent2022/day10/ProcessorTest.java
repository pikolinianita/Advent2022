/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
public class ProcessorTest {
    
    @Test
    public void testPart1() {
        var result = new Processor(Data.testInput.split("\n")).part1();
        assertThat(result).isEqualTo(13140);
    }
    
    @Test
    public void testERealMethodPart1() {
        assertThat(new Processor(Data.realInput.split("\n")).part1())
                .isEqualTo(11780);
    }
    
    @Test
     public void testERealMethodPart2() {
         System.out.println("--------------------------------------Real------------------------------------");
        new Processor(Data.realInput.split("\n")).part2();
        System.out.println("--------------------------------------Real------------------------------------");
     }
     
    @Test
    public void testPart2() {
         System.out.println("--------------------------------------Test------------------------------------");
        new Processor(Data.testInput.split("\n")).part2();
         System.out.println("--------------------------------------Test------------------------------------");
    }
    
}
