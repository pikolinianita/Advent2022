/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.luccasso.advent2022.day7;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class FileSystem {
    
    //tree with directory an file structure
    FSNode root;
    
    //list of all dirs, all tree levels
    List<Dir> dirs;
    
    int PART_ONE_THRESH = 100000;
    int TOTAL_CAPACITY = 70000000;
    int REQUIRED_CAPACITY = 30000000;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    public FileSystem(String input) {
        dirs = new ArrayList<>();
        parse (input.split("\n"));
    }
    
    int size(){
        return root.size();
    }

    int part1(){
        return dirs.stream()
                .mapToInt(dir -> dir.size())
                .filter(size -> size <PART_ONE_THRESH)
                .sum();
    }
    
    int part2(){
        int free_space = TOTAL_CAPACITY - size();
        int required_size = REQUIRED_CAPACITY - free_space;
       
        return dirs.stream()
                .mapToInt(dir -> dir.size())
                .filter(node -> node > required_size)
                .sorted()
                .findFirst()
                .orElseThrow(()-> new RuntimeException("err in part 2"));
    }
    
    private void parse(String[] input) {
       root = new Dir(null, "root");
       FSNode current= root;
        for (String line : input) {
            if (line.startsWith("$ cd /")){
                current = root;
            } else if (line.startsWith("$ cd ..")){
                current = current.up();
            } else if (line.startsWith("$ cd")){
                current = current.enterDir(line.substring(5));
            } else if (line.startsWith("$ ls")) {
                //do nothing
            } else if (line.startsWith("dir")){
                var dir = new Dir(current, line.substring(4));
                dirs.add(dir);
                current.add(dir);
            } else {
                current.add(new Fil(current,line));
            }
        }
    }
}
