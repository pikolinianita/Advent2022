/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day25;

import java.util.Scanner;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author piko
 */
public class Day25Test {
    
    String testInput = """
                       1=-0-2
                       12111
                       2=0=
                       21
                       2=01
                       111
                       20012
                       112
                       1=-1=
                       1-12
                       12
                       1=
                       122"""; 
            
    private static Stream<Arguments> provideSNAFU() {
        return Stream.of(
                Arguments.of(1, "1"),
                Arguments.of(2, "2"),
                Arguments.of(3, "1="),
                Arguments.of(4, "1-"),
                Arguments.of(5, "10"),
                Arguments.of(6, "11"),
                Arguments.of(7, "12"),
                Arguments.of(8, "2="),
                Arguments.of(9, "2-"),
                Arguments.of(10, "20"),
                Arguments.of(12, "22"),
                Arguments.of(13, "1=="),
                Arguments.of(14, "1=-"),
                Arguments.of(15, "1=0"),
                Arguments.of(16, "1=1"),
                Arguments.of(17, "1=2"),
                Arguments.of(18, "1-="),
                Arguments.of(19, "1--"),
                Arguments.of(20, "1-0"),
                Arguments.of(25, "100"),
                Arguments.of(37, "122"),
                Arguments.of(62, "222"),
                Arguments.of(2022, "1=11-2"),
                Arguments.of(12345, "1-0---0"),
                Arguments.of(314159265, "1121-1110-1=0")
        );
    }
    
    

    public Day25Test() {
    }

    @ParameterizedTest
    @MethodSource("provideSNAFU")
    public void testDecode(int decimal, String snafu) {
        System.out.println(snafu);
         assertThat(Day25.decode(snafu)).as(snafu + " should be " + decimal).isEqualTo(decimal);
    }

    @ParameterizedTest
    @MethodSource("provideSNAFU")
    public void testEncode(int decimal, String snafu) {
        System.out.println(snafu);
         assertThat(Day25.encode(decimal))
                 .as("" + decimal + " should be " + snafu)
                 .isEqualTo(snafu);
    }
    
    @Test
    void shouldCacheThrow(){
        assertThatException()
                .isThrownBy(() -> new Cache().get(28))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
     @Test
    void runOnce(){
        assertThat ( new Day25().calculateP1(new Scanner(testInput).tokens().toList()))
                .as("full p1 test data")
                .isEqualTo("2=-1=0");
    }
    
}
