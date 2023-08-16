/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day9;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


/**
 *
 * @author Nauczyciel
 */
public class BridgeTest {
    
    String testP1 = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2""";
    
    String testP2 = """
                    R 5
                    U 8
                    L 8
                    D 3
                    R 17
                    D 10
                    L 25
                    U 20""";
    
    @Test
    public void testPart1() {
       var bridge = new Bridge(Arrays.asList(testP1.split("\n")), 2);
       var result = bridge.simulate();
       assertThat(result).isEqualTo(13);
    }
    
    @Test
    public void testPart2() {
       var bridge = new Bridge(Arrays.asList(testP2.split("\n")), 10);
       var result = bridge.simulate();
       assertThat(result).isEqualTo(36);
    }
    
}
