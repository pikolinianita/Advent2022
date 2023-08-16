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
        var countBeaconless = buildRow(row)
               .count();
        return countBeaconless;
    }
    
    public long part2(int n) {
        for (int i = 0; i < n; i++) {
            var result = buildRow(i).verify(n);
            if (result > 0) {
                return result * 4000000 + i;
            }
        }
        throw new RuntimeException("part 2 mistake");
    }

    private RangesInRow buildRow(int row) {
        return sensors.stream()
                .filter(s -> s.rowInRange(row))
                .map(s -> s.rangeInRow(row))
                .reduce(new RangesInRow(),
                        ( RangesInRow RiR,RangeR r)-> RiR.add(r),
                        (r,rx) -> {throw new RuntimeException("no Parallel");});
    }
}

record Pos(int x, int y){};

record RangeR(int left, int right){};

record Sensor(Pos sensor, Pos beacon){
    
Sensor(int x, int y, int bx, int by){
    this(new Pos(x,y), new Pos(bx, by));
    }

    int manhattanR (){
        return abs(sensor.x()-beacon.x())+abs(sensor.y() - beacon.y());
    }
    
    boolean rowInRange(int row){
        var m = manhattanR();
        return (sensor.y() <= row) && (sensor.y() + m >= row) || (sensor.y() >= row) && (sensor.y() - m <= row);
    }
    
    RangeR rangeInRow(int row){
        int width = manhattanR() - abs(row - sensor.y());
        return new RangeR(sensor.x() - width, sensor.x() + width);
    }
}