/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day3;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
class Day3Test {

    List<String> testSource = List.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw");

    @Test
     void testOneElfP1() {
        int result = new Day3(" ").getPriorityOfCommonElement(testSource.get(0));
        assertThat(result).isEqualTo(16);
    }

     @Test
     void testAllElfsP1() {
        var day = new Day3("_");
        var result = testSource.stream()
                .mapToInt(line -> day.getPriorityOfCommonElement(line) )
                .sum();
         assertThat(result).isEqualTo(157);
    }
     
    @Test
    void testOneTriple(){
        var result = new Day3("_").countTeamBadge(testSource.get(0), testSource.get(1), testSource.get(2));
        assertThat(result).isEqualTo(18);
    }
    
    @Test
    void testAllTriples(){
        var result = new Day3("_").doPart2(testSource);
        assertThat(result).isEqualTo(70);
    }
     
}
