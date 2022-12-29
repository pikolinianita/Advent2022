/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day13;

import java.util.Scanner;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author piko
 */
public class Day13Test {

    String fullInput = """
                       [1,1,3,1,1]
                       [1,1,5,1,1]
                       
                       [[1],[2,3,4]]
                       [[1],4]
                       
                       [9]
                       [[8,7,6]]
                       
                       [[4,4],4,4]
                       [[4,4],4,4,4]
                       
                       [7,7,7,7]
                       [7,7,7]
                       
                       []
                       [3]
                       
                       [[[]]]
                       [[]]
                       
                       [1,[2,[3,[4,[5,6,7]]]],8,9]
                       [1,[2,[3,[4,[5,6,0]]]],8,9]""";

    public Day13Test() {
    }

    @DisplayName("Should parse packet line")
    @ParameterizedTest(name = "{index} => message=''{0}''")
    @ValueSource(strings = {
        "[1,1,3,1,1]",
        "[1,1,5,1,1]",
        "[[1],[2,3,4]]",
        "[[1],4]",
        "[20,[10],400]",
        "[9]",
        "[[8,7,6]]",
        "[[4,4],4,4]",
        "[[4,4],4,4,4]",
        "[7,7,7,7]",
        "[7,7,7]",
        "[]",
        "[3]",
        "[[[]]]",
        "[[]]",
        "[1,[2,[3,[4,[5,6,7]]]],8,9]",
        "[1,[2,[3,[4,[5,6,0]]]],8,9]"
    })
    void testNotSimpleListBuilder(String input) {
        
        assertThat(PacketBuilder.createPacket(input).toString().replaceAll(" ", ""))
                .isNotBlank()
                .isEqualTo(input);
    }
    
 private static Stream<Arguments> providePairs() {
        return Stream.of(
                Arguments.of("[1,1,3,1,1]", "[1,1,5,1,1]", -1),
                Arguments.of("[[1],[2,3,4]]", "[[1],4]", -1),
                Arguments.of("[9]", "[[8,7,6]]", 1),
                Arguments.of("[[4,4],4,4]", "[[4,4],4,4,4]", -1),
                Arguments.of("[7,7,7,7]", "[7,7,7]", 1),
                Arguments.of("[]", "[3]", -1),
                Arguments.of("[[[]]]", "[[]]", 1),
                Arguments.of("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]", 1)
        );
    }
 
    @ParameterizedTest(name = "{index} => packet= {0} ")
    @MethodSource("providePairs")
    void compareLines(String first, String second, int result) {
        
        var comparator = new NodeComparator();
        assertThat(Integer.signum(comparator
                .compare(PacketBuilder.createPacket(first), PacketBuilder.createPacket(second))))
                .isEqualTo(result);
    }

    @Test
    public void calculateP1Test() {
        
        var x = new Scanner(fullInput).tokens().toList();
        var result = new Day13(x).calculateP1();
        assertThat(result).isEqualTo(13);
    }
    
    @Test
    public void calculateP2Test() {
        
        var x = new Scanner(fullInput).tokens().toList();
        var result = new Day13(x).calculateP2();
        assertThat(result).isEqualTo(140);
    }
}
