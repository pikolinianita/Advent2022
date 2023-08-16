/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day15;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nauczyciel
 */
public class RangesInRow {

    List<RangeR> ranges;
    List<RangeR> forDelete;

    public RangesInRow() {
        this.ranges = new ArrayList<>();
        this.forDelete = new ArrayList<>();
    }
    
    RangesInRow add(RangeR r) {
        var current = r;        
        for (RangeR other: ranges) {
            if (canJoin(current,other)){
                current = merge(current, other);
                forDelete.add(other);
            }
        }        
        ranges.removeAll(forDelete);
        ranges.add(current);
        forDelete.clear();
        return this;
    }
    
    RangeR merge(RangeR r1, RangeR r2){
        return new RangeR(min(r1.left(),r2.left()), max(r1.right(),r2.right()));
    }

    private boolean canJoin(RangeR current, RangeR other) {
        return (current.left()<other.left()) ? touchOrOverlap(current, other) : touchOrOverlap(other,current);
    }

    private boolean touchOrOverlap(RangeR left, RangeR right) {
        return left.right()+1 >= right.left();
    }

    public int count(){
       System.out.println(toString());
        return ranges.stream().mapToInt(r -> r.right()-r.left()+1).sum();
    }

    @Override
    public String toString() {
        return "RangesInRow{" + "ranges=" + ranges  +'}';
    }
     
    long verify(int n){
        if(ranges.stream().filter(r -> r.left() >0 && r.left()<= n).count() >0){
            return ranges.stream()
                    .mapToInt(r -> r.left())
                    .filter(left -> left > 0)
                    .findFirst()
                    .getAsInt() - 1;
        };
        
        return -1;
    }
}
