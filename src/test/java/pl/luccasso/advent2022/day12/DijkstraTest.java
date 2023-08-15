/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day12;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Teresa
 */
public class DijkstraTest {
    
    String testInput = """
                       Sabqponm
                       abcryxxl
                       accszExk
                       acctuvwj
                       abdefghi""";
    
    Pathfinding sut = new Pathfinding(testInput,1);

    @Test
    public void testSomeMethod() {
        var dijkstra = Dijkstra.withStrategyPart(1);
        var result = dijkstra.solve(sut.start, sut.end, sut.board);
        System.out.println(result);
        System.out.println(dijkstra);
    }
    
}