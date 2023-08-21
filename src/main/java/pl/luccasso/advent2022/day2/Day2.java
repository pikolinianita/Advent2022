/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToIntFunction;

/**
 *
 * @author Nauczyciel
 */
enum Hand {

    ROCK(1),
    PAPER(2),
    SCISSOR(3);

    private int point;

    private static final Map<Hand, Hand> winsWith = Map.of(PAPER, ROCK,
            ROCK, SCISSOR,
            SCISSOR, PAPER);
    private static final Map<Hand, Hand> loseWith = Map.of(PAPER, SCISSOR,
            SCISSOR, ROCK,
            ROCK, PAPER);

    Hand(int point) {
        this.point = point;
    }

    public boolean winsWith(Hand other) {
        return winsWith.get(this).equals(other);
    }

    public Hand findWorse() {
        return winsWith.get(this);
    }

    public Hand findBetter() {
        return loseWith.get(this);
    }

    public int getPoint() {
        return point;
    }

    static int score(Hand other, Hand me) {
        if (other.equals(me)) {
            return 3;
        } else if (other.winsWith(me)) {
            return 0;
        } else {
            return 6;
        }

    }
}

public class Day2 {

    public Day2(String path) {
        this.path = path;
    }

    private List<String> input;
    private final String path;

    static Map<String, Hand> translate = Map.of(
            "A", Hand.ROCK,
            "B", Hand.PAPER,
            "C", Hand.SCISSOR,
            "X", Hand.ROCK,
            "Y", Hand.PAPER,
            "Z", Hand.SCISSOR);

    static int score(String line) {
        var arr = line.split(" ");
        return Hand.score(translate.get(arr[0]), translate.get(arr[1])) + translate.get(arr[1]).getPoint();
    }

    static int score2(String line) {
        var arr = line.split(" ");
        return findHand(arr[0], arr[1]).getPoint() + matchValueP2(arr[1]);
    }

    private static int matchValueP2(String value) {
        return switch (value) {
            case "X" -> 0;
            case "Y" -> 3;
            case "Z" -> 6;
            default ->
                throw new IllegalArgumentException("wrong String + value");
        };
    }

    private static Hand findHand(String other, String me) {
        var enemy = translate.get(other);

        return switch (me) {
            case "Y" -> enemy;
            case "X" -> enemy.findWorse();
            case "Z" -> enemy.findBetter();
            default ->
                throw new IllegalArgumentException("Wrong input value: " + me);
        };
    }

    public static void main(String[] args) throws FileNotFoundException {
        var day = new Day2("day02.txt").loadFile();

        int resultP1 = day.getP1Result();
        int resultP2 = day.getP2Result();

        System.out.println("result is: " + resultP1);
        System.out.println("result is: " + resultP2);
    }

    private int getP1Result() {
        return calculate(input, Day2::score);
    }

    private int getP2Result() {
        return calculate(input, Day2::score2);
    }

    private Day2 loadFile() throws FileNotFoundException {
        try (var sc = new Scanner(new File(path))) {
            input = sc
                    .useDelimiter("\n")
                    .tokens()
                    .toList();
        }
        return this;
    }

    int calculate(List<String> source, ToIntFunction<String> command) {
        return source.stream()
                .mapToInt(command)
                .sum();
    }
}
