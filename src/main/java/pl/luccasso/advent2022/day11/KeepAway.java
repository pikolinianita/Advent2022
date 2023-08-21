/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day11;

import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Nauczyciel
 */
public class KeepAway {

    List<Monkey> monkeys;

    final private String input;

    Pattern numberPatt = Pattern.compile("[0-9]+");

    long masterDivider;

    public KeepAway(String input) {
        this.input = input;

    }

    private List<Monkey> makeMonkeys(String input, Function<String, Function<Long, Long>> worryFunction) {
        return Arrays.stream(input.split("\n\n"))
                .map(description -> makeMonkey(description, worryFunction))
                .toList();
    }

    private Monkey makeMonkey(String description, Function<String, Function<Long, Long>> worryFunction) {
        var monkeyArr = description.split("\n");
        getNumber(monkeyArr[0]);

        var monkey = new Monkey(
                getNumber(monkeyArr[0]),
                getManyNumbers(monkeyArr[1]),
                worryFunction.apply(monkeyArr[2]),
                getNumber(monkeyArr[3]),
                getNumber(monkeyArr[4]),
                getNumber(monkeyArr[5]));
        return monkey;
    }

    private long getNumber(String line) {
        return parseLong(numberPatt.matcher(line).results().map(mat -> mat.group()).findAny().get());
    }

    private List<Long> getManyNumbers(String line) {
        return numberPatt.matcher(line)
                .results()
                .map(mat -> parseLong(mat.group()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Function<Long, Long> worryFunctionPart1(String line) {
        String[] operation = line.split("old ")[1].split(" ");
        if (operation[0].equals("+")) {
            return n -> (n + parseLong(operation[1])) / 3;
        } else if (operation[1].contains("old")) {
            return n -> n * n / 3;
        } else {
            return n -> (n * parseLong(operation[1])) / 3;
        }

    }

    private Function<Long, Long> worryFunctionPart2(String line) {
        String[] operation = line.split("old ")[1].split(" ");
        if (operation[0].equals("+")) {
            return n -> (n + parseLong(operation[1])) % masterDivider;
        } else if (operation[1].contains("old")) {
            return n -> (n * n) % masterDivider;
        } else {
            return n -> (n * parseLong(operation[1])) % masterDivider;
        }

    }

    @Override
    public String toString() {
        return "KeepAway{" + "monkeys=" + monkeys + '}';
    }

    long simulateKeepAway(int rounds, Function<String, Function<Long, Long>> worryFunction) {
        
        monkeys = populateMonkeyTroop(worryFunction);
        for (int n = 0; n < rounds; n++) {
            for (int count = 0; count < monkeys.size(); count++) {
                monkeys.get(count).inspect();
            }
        }
        return monkeyBusiness(monkeys);
    }

    private long monkeyBusiness(List<Monkey> troop) {
        return troop.stream()
                .mapToLong(m -> m.business)
                .sorted()
                .skip(troop.size() - 2)
                .reduce((a, b) -> a * b)
                .getAsLong();
    }

    private List<Monkey> populateMonkeyTroop(Function<String, Function<Long, Long>> worryFunction) {
        var monkeysInConstruction = makeMonkeys(input, worryFunction);
        monkeysInConstruction.forEach(monkey -> monkey.addTroop(monkeysInConstruction));
        masterDivider = monkeysInConstruction.stream()
                .mapToLong(monkey -> monkey.divider)
                .reduce((a, b) -> a * b).getAsLong();
        return monkeysInConstruction;
    }

    long part1(int rounds) {
        return simulateKeepAway(rounds, this::worryFunctionPart1);
    }

    long part2(int rounds) {
        return simulateKeepAway(rounds, this::worryFunctionPart2);
    }

}
