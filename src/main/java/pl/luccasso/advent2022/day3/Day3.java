/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Nauczyciel
 */
public class Day3 {

    String path;
    private List<String> input;
    
    public Day3(String path) {
        this.path = path;
    }

     int getPriorityOfCommonElement(String line) {
        var splitPoint = line.length() / 2;
        Set<String> firstCompartment = charsSet(line.substring(splitPoint));
        Set<String> wantedItem = commonLetters(firstCompartment, line.substring(0, splitPoint));
        return getPriority(wantedItem);
    }

      Set<String> charsSet(String s) {
        return Arrays.stream(s.split(""))
                .collect(Collectors.toSet());
    }

     Set<String> commonLetters(Set<String> set, String line) {
        return Arrays.stream(line.split(""))
                .filter(set::contains)
                .collect(Collectors.toSet());
    }

    private  int getPriority(int i) {
        return (i > 96) ? i - 96 : i - 64 + 26;
    }
    
    private int getPriority(Set<String> set){
        return getPriority(set.iterator().next().codePointAt(0));
    } 

    private Day3 loadFile() throws FileNotFoundException {
        try (var sc = new Scanner(new File(path))) {
            input = sc.tokens().toList();
            return this;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        var day = new Day3("day03.txt").loadFile();
        int resultP1 = day.calculateP1();
        int resultP2 = day.calculateP2();
        System.out.println("Part 1: " + resultP1);
        System.out.println("Part 1: " + resultP2);
    }

    private int calculateP1() {
        return doPart1(input);
    }
    
    private int calculateP2() {
        return doPart2(input);
    }

    int doPart1(List<String> input) {
        return input.stream()
                .mapToInt(this::getPriorityOfCommonElement)
                .sum();
    }

    int doPart2(List<String> input) {
        var iter = input.iterator();
        int sum = 0;
        while (iter.hasNext()){
            sum += countTeamBadge(iter.next(), iter.next(), iter.next());
        }
        return sum;
    }

    int countTeamBadge(String elf1, String elf2, String elf3) {
        var set = charsSet(elf1);
        var set2 = commonLetters(set, elf2);
        var set3 = commonLetters(set2, elf3);
        return getPriority(set3.iterator().next().codePointAt(0));
    }

}
