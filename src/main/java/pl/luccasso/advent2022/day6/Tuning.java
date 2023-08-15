/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.luccasso.advent2022.day6;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Teresa
 */
public class Tuning {

    
    static int findStart(String input){
        return findAllDifferent(input, 4);
    }

    static int findMessage(String input){
        return findAllDifferent(input, 14);
    }
    
    private static boolean isAllDifferent(int size, String [] arr) {
       return new HashSet(Arrays.asList(arr)).size() == size;
    }
    
    static int findAllDifferent(String input, int size){
        var arr = input.split("");
        for (int i = size; i<input.length(); i++){
            if (isAllDifferent(size, Arrays.copyOfRange(arr, i-size, i)))
                return i;
        }
        throw new RuntimeException("start not found: " + input);
    }
}
