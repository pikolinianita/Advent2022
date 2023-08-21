/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day15;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Nauczyciel
 */
class BoardTest {
    
   String input = """
                  Sensor at x=3999724, y=2000469: closest beacon is at x=4281123, y=2282046
                  Sensor at x=3995530, y=8733: closest beacon is at x=3321979, y=-692911
                  Sensor at x=3016889, y=2550239: closest beacon is at x=2408038, y=2645605
                  Sensor at x=3443945, y=3604888: closest beacon is at x=3610223, y=3768674
                  Sensor at x=168575, y=491461: closest beacon is at x=1053731, y=-142061
                  Sensor at x=2820722, y=3865596: closest beacon is at x=3191440, y=3801895
                  Sensor at x=2329102, y=2456329: closest beacon is at x=2408038, y=2645605
                  Sensor at x=3889469, y=3781572: closest beacon is at x=3610223, y=3768674
                  Sensor at x=3256726, y=3882107: closest beacon is at x=3191440, y=3801895
                  Sensor at x=3729564, y=3214899: closest beacon is at x=3610223, y=3768674
                  Sensor at x=206718, y=2732608: closest beacon is at x=-152842, y=3117903
                  Sensor at x=2178192, y=2132103: closest beacon is at x=2175035, y=2000000
                  Sensor at x=1884402, y=214904: closest beacon is at x=1053731, y=-142061
                  Sensor at x=3060435, y=980430: closest beacon is at x=2175035, y=2000000
                  Sensor at x=3998355, y=3965954: closest beacon is at x=3610223, y=3768674
                  Sensor at x=3704399, y=3973731: closest beacon is at x=3610223, y=3768674
                  Sensor at x=1421672, y=3446889: closest beacon is at x=2408038, y=2645605
                  Sensor at x=3415633, y=3916020: closest beacon is at x=3191440, y=3801895
                  Sensor at x=2408019, y=2263990: closest beacon is at x=2408038, y=2645605
                  Sensor at x=3735247, y=2533767: closest beacon is at x=4281123, y=2282046
                  Sensor at x=1756494, y=1928662: closest beacon is at x=2175035, y=2000000
                  Sensor at x=780161, y=1907142: closest beacon is at x=2175035, y=2000000
                  Sensor at x=3036853, y=3294727: closest beacon is at x=3191440, y=3801895
                  Sensor at x=53246, y=3908582: closest beacon is at x=-152842, y=3117903
                  Sensor at x=2110517, y=2243287: closest beacon is at x=2175035, y=2000000
                  Sensor at x=3149491, y=3998374: closest beacon is at x=3191440, y=3801895""";
   
   String testInput = """
                      Sensor at x=2, y=18: closest beacon is at x=-2, y=15
                      Sensor at x=9, y=16: closest beacon is at x=10, y=16
                      Sensor at x=13, y=2: closest beacon is at x=15, y=3
                      Sensor at x=12, y=14: closest beacon is at x=10, y=16
                      Sensor at x=10, y=20: closest beacon is at x=10, y=16
                      Sensor at x=14, y=17: closest beacon is at x=10, y=16
                      Sensor at x=8, y=7: closest beacon is at x=2, y=10
                      Sensor at x=2, y=0: closest beacon is at x=2, y=10
                      Sensor at x=0, y=11: closest beacon is at x=2, y=10
                      Sensor at x=20, y=14: closest beacon is at x=25, y=17
                      Sensor at x=17, y=20: closest beacon is at x=21, y=22
                      Sensor at x=16, y=7: closest beacon is at x=15, y=3
                      Sensor at x=14, y=3: closest beacon is at x=15, y=3
                      Sensor at x=20, y=1: closest beacon is at x=15, y=3""";

    
     @Test
    void testSomeMethod2() {
       var board = new Board(testInput.split("\n"));
       var result = board.part1(10) -1 ;
       assertThat(result).isEqualTo(26);
       //-1 because of beacon position
    }
    
     @Test
    void testRealData() {
       var board = new Board(input.split("\n"));
       var result = board.part1(2000000) -1;
       assertThat(result).isEqualTo(5176944);
       //-1 because of beacon position
    }
    
    @Test
    void testP2TestBruteForce() {
        var board = new Board(testInput.split("\n"));
        var result = board.part2(20);
        System.out.println(result);
        assertThat(result).isEqualTo(56000011);
    }
     
     @Test
     void testP2FullBruteForce() {
       var board = new Board(input.split("\n"));       
            var result = board.part2(4000000);            
            assertThat(result).isEqualTo(13350458933732L);
     }
    
    @Test
    void testMax() {
        assertThat(new Sensor(0, 11, 2, 10).hasUnoccupiedInRow(14)).isTrue();
    }
    
    @Test
    void testEqualRow() {
        assertThat(new Sensor(0, 11, 2, 10).hasUnoccupiedInRow(11)).isTrue();
    }
}
