/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Nauczyciel
 */
public class Day4 {
    String path;
    List<String> lines;
    private final Pattern patt;

    public Day4(String path) {
        this.path = path;
         patt = Pattern.compile("-|,");
    }

    boolean needReconsideration(String line) {
        return check(patt.split(line)) ;
    }
    
    boolean noOverlap(String line){
        return checkp2(patt.split(line));
        
    }

    long solvePart1(List<String> list) {
       return list.stream()
                .filter(s -> needReconsideration(s))               
               .count();
               
    }
    
    long resultPart1(){
        return solvePart1(lines);
    }
    
    long solvePart2(List<String> list) {
       return list.stream()
                .filter(s -> noOverlap(s))
               .count();
               
    }
    
    long resultPart2(){
        return solvePart2(lines);
    }

    private boolean check(String[] line) {
        int[] a ={
            Integer.parseInt(line[0]),
                Integer.parseInt(line[1]),
                Integer.parseInt(line[2]),
                Integer.parseInt(line[3])        
        };
        
       return ((a[0] >= a[2])&&(a[1] <= a[3]))
           || ((a[0] <= a[2])&&(a[1] >= a[3]));
    }
    
    
    
    Day4 loadFile() throws FileNotFoundException{
         lines = new Scanner(new File(path)).tokens().toList();
        return this;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        var day = new Day4("day04.txt").loadFile();
        
        System.out.println("Part1: " + day.resultPart1());
        System.out.println("Part2: " + day.resultPart2());
    }

    private boolean checkp2(String[] line) {
         int[] a ={
            Integer.parseInt(line[0]),
                Integer.parseInt(line[1]),
                Integer.parseInt(line[2]),
                Integer.parseInt(line[3])        
        };
         
         System.out.println(Arrays.toString(line) + " " + ((a[1] < a[2])|| (a[3] < a[0])));
        
       return (a[1] < a[2])
           || (a[3] < a[0]);
    }
    //126 too low
    
}
