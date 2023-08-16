/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day9;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


/**
 *
 * @author Nauczyciel
 */
public class BridgeTest {
    
    String input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2""";
    
    @Test
    public void testSomeMethod() {
       var bridge = new Bridge(Arrays.asList(input.split("\n")));
       var result = bridge.part1();
       assertThat(result).isEqualTo(13);
    }
    
}
