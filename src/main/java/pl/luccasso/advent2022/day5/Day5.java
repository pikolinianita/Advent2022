/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Nauczyciel
 */

record CommandLine(int n, int from, int to){
    
}

class Supplies {

    private Deque<String>[] stacks;
    int size;

    public Supplies(List<String> header) {
        size = findSize(header);
       // System.out.println(size);
        createStack(size);
        header.forEach(s -> System.out.println(Arrays.toString(s.split(""))));
        for (int i =header.size()-2; i>-1 ; i--){
            applyLine(header.get(i));
        }
    }

     private void applyLine(String line) {
         for (int i=0; i<size; i++){
             //System.out.println("line: " + line + " " + i);
             var sign = line.substring(i * 4 +1, i*4 + 2);
             if (! sign.isBlank()){
              //   System.out.println(sign);
                 push(i+1, sign);
             }
         }
       
    }
    
    private void createStack(int size) {
        stacks = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            stacks[i] = new LinkedList<>();
        }
    }

    private Supplies move(int from, int to, int n) {
        for (int i = 0; i < n; i++) {
            stacks[to - 1].push(stacks[from - 1].pop());
        }
        return this;
    }

    private Supplies push(int to, String s) {
        stacks[to - 1].push(s);
        return this;
    }

    public String topLayer() {
        var result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(stacks[i].peek());
        }
        return result.toString();
    }

    private int findSize(List<String> header) throws NumberFormatException {
        var lastString = header.get(header.size() - 1).stripTrailing().split("");
        //System.out.println("------ " + Arrays.toString( lastString));
        return Integer.parseInt(lastString[lastString.length - 1]);

    }

    public Supplies rearrange(List<CommandLine> commands) {
        commands.forEach(c-> move(c.from(), c.to(), c.n()));
        return this;
    }

   
    
}

class Parser {

    private List<String> header;
    private List<String> moves;

    public Parser() {

    }

    public Parser parse(Scanner sc) {
        var lists = sc.useDelimiter("\n")
                .tokens()
                .filter(s -> !"".equals(s))
                .collect(Collectors.groupingBy(this::isCommand));
      //  System.out.println(lists);
        header = lists.get(false);
        moves = lists.get(true);
        return this;
    }

    boolean isCommand(String line) {
        return line.startsWith("move");
    }

    public List<String> getHeader() {
        return header;
    }

    public List<CommandLine> getMoves() {
        return moves.stream().map(this::toCommandLine).toList();
        
    }

    CommandLine toCommandLine(String s){
       String[] res = s.split("[a-zA-Z]+");
       
       return new CommandLine(Integer.parseInt(res[1].strip()), 
               Integer.parseInt(res[2].strip()), 
               Integer.parseInt(res[3].strip()));
    }
    
}

public class Day5 {

    List<String> commands;
    Parser parser;
    
    public static void main(String[] args) throws FileNotFoundException {
        var day = new Day5().parse("day05.txt");
       var supplies = new Supplies(day.parser.getHeader());
        System.out.println(supplies.topLayer());
        supplies.rearrange(day.parser.getMoves());
        System.out.println("result is: " + supplies.topLayer());
    }

    private Day5 parse(String path) throws FileNotFoundException{
        parser = new Parser().parse(new Scanner(new File(path)));
        
        return this;
    }
    
    
}
