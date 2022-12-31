/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day12;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Nauczyciel
 */

record Point(int x, int y){};

class Board{
    
    int[][] board;
    Point start;
    Point end;
    int sizeX;
    int sizeY;
    
    static Board makeBoard(List<String> lines){
       var sizeX = lines.size();
       var sizeY = lines.get(0).length();
       var board = lines.stream()
               .map(line -> line.codePoints().toArray())
               .toArray(int[][]::new);
        
       var result = new Board(board);
       
       result.setSizeX(sizeX)
               .setSizeY(sizeY)
               .setEnd(result.findPoint('E'))
               .setStart(result.findPoint('S'));
       
       return result;
    }
    
    private Point findPoint(char c) {
        for (int i =0; i<sizeX; i++)
            for(int j=0; j<sizeY; j++){
                if(get(i,j)==c)
                    return new Point(i,j);
            }
       throw new IllegalArgumentException("char not found: " + c);
    }
    Board(int[][] board ){
        this.board = board;
    }
    
    int get (int x, int y){
        return isInRange(x,y) ? board [x][y] : Integer.MAX_VALUE;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    private Board setStart(Point start) {
        this.start = start;
        return this;
    }

    private Board setEnd(Point end) {
        this.end = end;
        return this;
    }

    private Board setSizeX(int sizeX) {
        this.sizeX = sizeX;
        return this;
    }

    private Board setSizeY(int sizeY) {
        this.sizeY = sizeY;
        return this;
    }   

    @Override
    public String toString() {
        return "Board{" + "board=" + Arrays.deepToString(board) + ", start=" + start + ", end=" + end + '}';
    }

    private boolean isInRange(int x, int y) {
       
        return (x >= 0) && ( x < sizeX) && (y >= 0) && (y < sizeY);
    }
    
    
}

public class Day12 {
    
}
