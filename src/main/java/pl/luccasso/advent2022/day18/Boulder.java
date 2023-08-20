/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day18;

import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Nauczyciel
 */
public class Boulder {

    private final List<Point3D> points;
    
    public Boulder(String[] lines) {

        points = Arrays.stream(lines)
                .map(line -> {
                    var splitted = line.split(",");
                    return new Point3D(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
                }).toList();
    }

    @Override
    public String toString() {
        return "Boulder{" + "points=" + points + '}';
    }

    int part1() {
        var result = points.stream().flatMap(point -> point.neighbours())
                .collect(Collectors.toMap(point -> point, (point) -> 1, (o, n) -> o + n));
        points.forEach(p -> result.remove(p));
        return result.values().stream().reduce(0, (a, b) -> a + b);
    }

    int part2() {
        return new Part2Calculator(points).calculate();
    }
}

class Part2Calculator {

    private final List<Point3D> points;
    private int counted;
    private final Queue<Point3D> toVisit;
    private final HashSet<Point3D> visited;
    private final IntSummaryStatistics statZ;
    private final IntSummaryStatistics statY;
    private final IntSummaryStatistics statX;

    public Part2Calculator(List<Point3D> points) {
        this.points = points;
        statX = points.stream().mapToInt(Point3D::x).summaryStatistics();
        statY = points.stream().mapToInt(Point3D::y).summaryStatistics();
        statZ = points.stream().mapToInt(Point3D::z).summaryStatistics();
        visited = new HashSet<>();
        toVisit = new LinkedList<>();
        counted = 0;
    }

    public int calculate() {
        var startPoint = new Point3D(statX.getMin() - 1, statY.getMin() - 1, statZ.getMin() - 1);
        toVisit.add(startPoint);
        processPoint();
        return counted;
    }

    private void processPoint() {
        while (!toVisit.isEmpty()) {
            var currentPoint = toVisit.poll();
            if (points.contains(currentPoint)) {
                counted++;
            } else if (visited.contains(currentPoint))
            {
                //skip it, do nothing
            }
            else {
                visited.add(currentPoint);
                currentPoint.neighbours()                       
                        .filter(this::isInsideBoundaries)
                        .filter(point -> !visited.contains(point))                       
                        .forEach(point -> toVisit.offer(point));
            }
        }
    }

    private boolean isInsideBoundaries(Point3D p) {
        return ((p.x() >= statX.getMin() - 1) && (p.x() <= statX.getMax() + 1)
                && (p.y() >= statY.getMin() - 1) && (p.y() <= statY.getMax() + 1)
                && (p.z() >= statZ.getMin() - 1) && (p.z() <= statZ.getMax() + 1));
    }
}

record Point3D(int x, int y, int z) {

    public Stream<Point3D> neighbours() {
        return Stream.of(
                new Point3D(x + 1, y, z),
                new Point3D(x - 1, y, z),
                new Point3D(x, y + 1, z),
                new Point3D(x, y - 1, z),
                new Point3D(x, y, z + 1),
                new Point3D(x, y, z - 1));
    }
}
