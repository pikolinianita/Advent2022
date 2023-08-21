/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day10;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
class ProcessorTest {
    
    @Test
    void testPart1() {
        var result = new Processor(Data.testInput.split("\n")).part1();
        assertThat(result).isEqualTo(13140);
    }
    
    @Test
    void testERealMethodPart1() {
        assertThat(new Processor(Data.realInput.split("\n")).part1())
                .isEqualTo(11780);
    }
    
    @Test
     void testRealMethodPart2() {
        new Processor(Data.realInput.split("\n")).part2();
        //see system Output
     }
     
    @Test
    void testTestPart2() {
        new Processor(Data.testInput.split("\n")).part2();
        //see system output
    }
    
}
