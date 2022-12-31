/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day5;

import java.util.Scanner;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Nauczyciel
 */
public class Day5Test {
    
    String input = """
                       [D]   ] 
                   [N] [C]   ] 
                   [Z] [M] [P]
                    1   2   3 
                   
                   move 1 from 2 to 1
                   move 3 from 1 to 3
                   move 2 from 2 to 1
                   move 1 from 1 to 2""";
    
    
    public Day5Test() {
    }

    @Test
    public void testParser() {
        var parser = new Parser().parse(new Scanner(input));
        System.out.println(parser.getHeader());
        assertThat(parser.getHeader()).hasSize(4);
        assertThat(parser.getMoves())
                .hasSize(4)
                .allMatch(s -> s.from()>0 && s.to()>0 && s.n()>0);
    }
    
    @Test
    public void testHeader(){
        var header = new Supplies(new Scanner(input)
                .useDelimiter("\n")
                .tokens()
                .limit(4)
                .toList());
        assertThat(header.topLayer()).isEqualTo("NDP");
    }
    
     @Test
    public void testRearrangeHeader(){
        var parser = new Parser().parse(new Scanner(input));
        var result = new Supplies(parser.getHeader()).rearrange(parser.getMoves());
        assertThat(result.topLayer()).isEqualTo("CMZ");
    }
    
    @Test
    void testLineParser(){
        var result = new Parser().toCommandLine("move 3 from 1 to 5");
        System.out.println(result);
        assertThat(result.n()).isEqualTo(3);
        assertThat(result.from()).isEqualTo(1);
        assertThat(result.to()).isEqualTo(5);
    }
}
