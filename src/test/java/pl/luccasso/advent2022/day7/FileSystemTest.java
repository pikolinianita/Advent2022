/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.luccasso.advent2022.day7;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
public class FileSystemTest {
    
   

    @Test
    public void testPart1() {        
        var result = new FileSystem(Data.testInput);   
        assertThat(result.part1()).isEqualTo(95437);        
    }
    
    @Test
    public void testFullPart1() {        
        var result = new FileSystem(Data.fullInput);     
        assertThat(result.part1()).isEqualTo(1307902);        
    }
    
     @Test
    public void testPart2() {        
        var result = new FileSystem(Data.testInput);   
        assertThat(result.part2()).isEqualTo(24933642);        
    }
    
    @Test
    public void testFullPart2() {        
        var result = new FileSystem(Data.fullInput);     
        assertThat(result.part2()).isEqualTo(7068748);        
    }
}
