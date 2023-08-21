/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day11;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Nauczyciel
 */
public class KeepAwayTest {

    String testInput = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                
                Monkey 1:
                  Starting items: 54, 65, 75, 74
                  Operation: new = old + 6
                  Test: divisible by 19
                    If true: throw to monkey 2
                    If false: throw to monkey 0
                
                Monkey 2:
                  Starting items: 79, 60, 97
                  Operation: new = old * old
                  Test: divisible by 13
                    If true: throw to monkey 1
                    If false: throw to monkey 3
                
                Monkey 3:
                  Starting items: 74
                  Operation: new = old + 3
                  Test: divisible by 17
                    If true: throw to monkey 0
                    If false: throw to monkey 1""";

    String realInput = """
                       Monkey 0:
                         Starting items: 85, 77, 77
                         Operation: new = old * 7
                         Test: divisible by 19
                           If true: throw to monkey 6
                           If false: throw to monkey 7
                       
                       Monkey 1:
                         Starting items: 80, 99
                         Operation: new = old * 11
                         Test: divisible by 3
                           If true: throw to monkey 3
                           If false: throw to monkey 5
                       
                       Monkey 2:
                         Starting items: 74, 60, 74, 63, 86, 92, 80
                         Operation: new = old + 8
                         Test: divisible by 13
                           If true: throw to monkey 0
                           If false: throw to monkey 6
                       
                       Monkey 3:
                         Starting items: 71, 58, 93, 65, 80, 68, 54, 71
                         Operation: new = old + 7
                         Test: divisible by 7
                           If true: throw to monkey 2
                           If false: throw to monkey 4
                       
                       Monkey 4:
                         Starting items: 97, 56, 79, 65, 58
                         Operation: new = old + 5
                         Test: divisible by 5
                           If true: throw to monkey 2
                           If false: throw to monkey 0
                       
                       Monkey 5:
                         Starting items: 77
                         Operation: new = old + 4
                         Test: divisible by 11
                           If true: throw to monkey 4
                           If false: throw to monkey 3
                       
                       Monkey 6:
                         Starting items: 99, 90, 84, 50
                         Operation: new = old * old
                         Test: divisible by 17
                           If true: throw to monkey 7
                           If false: throw to monkey 1
                       
                       Monkey 7:
                         Starting items: 50, 66, 61, 92, 64, 78
                         Operation: new = old + 3
                         Test: divisible by 2
                           If true: throw to monkey 5
                           If false: throw to monkey 1""";

    @Test
    public void testTestMethod() {
        var result = new KeepAway(testInput);
        assertThat(result.part1(20)).isEqualTo(10605);
    }

    @Test
    public void testRealMethod() {
        var result = new KeepAway(realInput);
        assertThat(result.part1(20)).isEqualTo(54752);
    }

    @Test
    public void testTestMethodPart2() {
        var result = new KeepAway(testInput);
        assertThat(result.part2(10000)).isEqualTo(2713310158L);
    }

    @Test
    public void testRealMethodPart2() {
        var result = new KeepAway(realInput);
        assertThat(result.part2(10000)).isEqualTo(13606755504L);
    }
}
