/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day2;

import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
public class Day2Test {

    @Test
    public void testSimple() {
        assertThat(Day2.score("A Y")).isEqualTo(8);
        assertThat(Day2.score("B X")).isEqualTo(1);
        assertThat(Day2.score("C Z")).isEqualTo(6);
    }

    @Test
    public void testAll() {
        var source = List.<String>of("A Y", "B X", "C Z");
        assertThat(new Day2("_").calculate(source, Day2::score)).isEqualTo(15);
    }

    @Test
    void Day2OneLineTest() {
        assertThat(Day2.score2("A Y")).isEqualTo(4);
        assertThat(Day2.score2("B X")).isEqualTo(1);
        assertThat(Day2.score2("C Z")).isEqualTo(7);
    }

    @Test
    public void testAllPart2() {
        var source = List.<String>of("A Y", "B X", "C Z");
        assertThat(new Day2("_").calculate(source, Day2::score2)).isEqualTo(12);
    }
}
