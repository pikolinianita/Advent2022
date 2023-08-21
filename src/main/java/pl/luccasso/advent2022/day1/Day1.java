/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Nauczyciel
 */
public class Day1 {

    String path;
    Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {

        var resultP1 = new Day1("day01.txt").getP1Result();
        var resultP2 = new Day1("day01.txt").getP2Result();

        System.out.println("result is: " + resultP1);
        System.out.println("result is: " + resultP2);
    }

    Day1(String string) {
        path = string;
    }

    long getP2Result() throws FileNotFoundException {
        try {
            loadFile(path);
            return calculateP2(sc);
        } finally {
            sc.close();
        }
    }

    long getP1Result() throws FileNotFoundException {
        try {
            loadFile(path);
            return calculateP1(sc);
        } finally {
            sc.close();
        }
    }

    private Day1 loadFile(String path) throws FileNotFoundException {
        sc = new Scanner(new File(path)).useDelimiter("\n");
        return this;
    }

    long calculateP1(Scanner sc) {
        long max = Long.MIN_VALUE;
        long tmp = 0;
        while (sc.hasNext()) {
            if (sc.hasNextLong()) {
                tmp += sc.nextLong();
            } else {
                if (max < tmp) {
                    max = tmp;
                }
                tmp = 0;
                sc.next();
            }
        }
        return max;
    }

    long calculateP2(Scanner sc) {
        Queue<Integer> queue = new LinkedList<>();
        int tmp = 0;
        while (sc.hasNext()) {
            if (sc.hasNextLong()) {
                tmp += sc.nextLong();
            } else {
                queue.offer(tmp);
                tmp = 0;
                sc.next();
            }
        }
        queue.offer(tmp);

        return queue.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0,(a, b) -> a + b);
    }
}
