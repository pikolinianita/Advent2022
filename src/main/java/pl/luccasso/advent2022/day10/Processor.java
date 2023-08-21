/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day10;

import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nauczyciel
 */
public class Processor {
    
    List<Integer> controlPoints = List.of(20,60,100,140,180,220);
    private static final int INITIAL_VALUE = 1;
    private static final int FINAL_TIME = 240;
    Map<Integer, Integer> values;
    Screen screen;

    public Processor(String[] lines) {
        screen = new Screen();
        values = new HashMap<>();
        int registry = INITIAL_VALUE;
        int time = 0;
        for (String line : lines) {
            var command = line.split(" ");
            if (command[0].equals("noop")) {
                time++;
                values.put(time, registry);
            } else {
                time += 2;
                values.put(time - 1, registry);
                values.put(time, registry);
                registry += Integer.parseInt(command[1]);
            }
            if (time > FINAL_TIME) {
                break;
            }
        }
    }
    
    public int part1(){
        return controlPoints.stream()
                .mapToInt(val->  val * values.get(val))
                .sum();
    }
    
    public void part2() {
        for (int i = 0; i < 6; i++) {
            fillRow(screen.display[i], i * 40);
        }
        System.out.println(screen.toString());
    }

    private void fillRow(String[] row, int offset) {
        for (int i = 0; i < 40; i++) {
            boolean check = abs(i - values.get(i + 1 + offset)) < 2;
            if (check) {
                row[i] = "#";
            } else {
                row[i] = ".";
            }
        }
    }
    
}

class Screen{
    String[][] display;

    public Screen() {
        display = new String[6][40];
    }
 
    @Override
    public String toString() {
        return Arrays.asList(display)
                .stream()
                .map(arr -> ("||" + Arrays.toString(arr) + "||\n"))
                .reduce(String::concat)
                .orElse("Err in drawing screen");
    }
    
    
}
