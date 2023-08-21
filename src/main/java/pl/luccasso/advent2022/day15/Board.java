/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day15;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nauczyciel
 */
public class Board {
    
    List<Sensor> sensors;
    
    public Board(String[] data){
        sensors = new ArrayList<>();
        for(String sensor : data){
            var splitted = sensor.split("[ ,=:]");
            sensors.add(new Sensor(
                    Integer.parseInt(splitted[3]), 
                    Integer.parseInt(splitted[6]), 
                    Integer.parseInt(splitted[13]), 
                    Integer.parseInt(splitted[16])));
        }
    }    
    
    public int part1(int row){
        return buildRow(row).countNoBeacon();
    }
    
    public long part2(int n) {
        for (int i = 0; i < n; i++) {
            var result = buildRow(i).verify(n);
            if (result > 0) {
                return result * 4000000L + i;
            }
        }
        throw new IllegalStateException("part 2 mistake");
    }

    private RangesInRow buildRow(int row) {
        return sensors.stream()
                .filter(s -> s.hasUnoccupiedInRow(row))
                .map(s -> s.findImpossiblesInRow(row))
                .reduce(new RangesInRow(),
                        (RangesInRow riR, Range r)-> riR.add(r),
                        (r,rx) -> {throw new IllegalStateException("no Parallel");});
    }
}

record Pos(int x, int y){};

record Range(int left, int right){};

record Sensor(Pos sensor, Pos beacon){
    
Sensor(int x, int y, int bx, int by){
    this(new Pos(x,y), new Pos(bx, by));
    }

    int manhattanRange (){
        return abs(sensor.x()-beacon.x())+abs(sensor.y() - beacon.y());
    }
    
    boolean hasUnoccupiedInRow(int row){
        var m = manhattanRange();
        return (sensor.y() <= row) && (sensor.y() + m >= row) || (sensor.y() >= row) && (sensor.y() - m <= row);
    }
    
    Range findImpossiblesInRow(int row){
        int width = manhattanRange() - abs(row - sensor.y());
        return new Range(sensor.x() - width, sensor.x() + width);
    }
}