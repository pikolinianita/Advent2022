package pl.luccasso.advent2022.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author piko
 */
class Appendix {

    private final StringBuilder value;

    public Appendix() {
        value = new StringBuilder();
    }

    public Appendix append(int number) {
        value.append(switch (number) {
            case 2 -> "2";
            case 1 -> "1";
            case 0 -> value.isEmpty() ? "" : "0";
            case -1 -> "-";
            case -2 -> "=";
            default ->
                throw new IllegalArgumentException("wrong value in Apppendix!!! : " + number);
        });
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}

class Cache {

    private static final int MAX_POWER = 27;

    private final Map<Integer, Long> cache;
    private int max;

    public Cache() {
        cache = new HashMap<>();
        cache.put(0, 1L);
        max = 0;
    }

    public long get(int power) {
        if (power > MAX_POWER) {
            throw new IllegalArgumentException("Result too large, Long too short - cache");
        }
        if (power > max) {
            while (power > max) {
                max++;
                cache.put(max, cache.get(max - 1) * 5);
            }
        }
        return cache.get(power);
    }
}

class Encoder {

    private final Cache cache;

    private final Appendix appendix;

    private long leftoverValue;

    private int sign;

    private long valueOfCurrentPowerOfFive;

    public Encoder(Cache cache) {
        appendix = new Appendix();
        sign = 1;
        this.cache = cache;
    }

    public String encode(long decimal) {

        int power = (int) Math.round(Math.log10(decimal) * 1.5) + 1;
        leftoverValue = decimal;
        while (power >= 0) {            
            valueOfCurrentPowerOfFive = cache.get(power);
            if (leftoverValue > valueOfCurrentPowerOfFive * 1.5) {
                nextDigitIsPlusMinus(2);
            } else if (leftoverValue > valueOfCurrentPowerOfFive / 2) {
                nextDigitIsPlusMinus(1);
            } else {
                nextDigitIsPlusMinus(0);
            }

            adjustSign();
            power--;
        }
        return appendix.toString();
    }

    private void adjustSign() {
        if (leftoverValue < 0) {
            sign *= -1;
            leftoverValue *= -1;
        }
    }

    private void nextDigitIsPlusMinus(int signValue) {
        appendix.append(signValue * sign);
        this.leftoverValue -= signValue * valueOfCurrentPowerOfFive;
    }

}

public class Day25 {

    static Cache cache = new Cache();

    private List<String> lines;
    
    public static long decode(String snafu) {

        var position = snafu.length();
        var arr = snafu.split("");
        int power = 0;
        long result = 0L;
        while (position > 0) {
            position--;
            result += cache.get(power) * getNumber(arr[position]);
            power++;
        }
        if (result < 0) {
            throw new IllegalArgumentException("long too short!!! decode: " + result);
        }
        return result;
    }

    private static int getNumber(String sign) {
        return switch (sign) {
            case "0" -> 0;
            case "1" -> 1;
            case "2" -> 2;
            case "-" -> -1;
            case "=" -> -2;
            default ->
                throw new IllegalArgumentException("should no happen! getNumber " + sign);
        };
    }

    public static String encode(long decimal) {
        return new Encoder(cache).encode(decimal);
    }

    private Day25 loadLines(String path) throws FileNotFoundException {
        try (var sc = new Scanner(new File(path))) {
            lines = sc.tokens().toList();
            return this;
        }
    }

    String calculateP1(List<String> lines) {
        return encode(lines.stream()
                .mapToLong(Day25::decode)
                .sum());
    }

    void calculateAndPrint() {
        System.out.println("Result for p1 is : " + calculateP1(lines));
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day25()
                .loadLines("day25.txt")
                .calculateAndPrint();
    }

}
