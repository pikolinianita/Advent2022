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

    List<Range> ranges;
    List<Range> forDelete;

    public RangesInRow() {
        this.ranges = new ArrayList<>();
        this.forDelete = new ArrayList<>();
    }
    
    RangesInRow add(Range r) {
        var current = r;        
        for (Range other: ranges) {
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
    
    Range merge(Range r1, Range r2){
        return new Range(min(r1.left(),r2.left()), max(r1.right(),r2.right()));
    }

    private boolean canJoin(Range current, Range other) {
        return (current.left()<other.left()) ? touchOrOverlap(current, other) : touchOrOverlap(other,current);
    }

    private boolean touchOrOverlap(Range left, Range right) {
        return left.right()+1 >= right.left();
    }

    public int countNoBeacon(){
        return ranges.stream().mapToInt(r -> r.right()-r.left()+1).sum();
    }

    @Override
    public String toString() {
        return "RangesInRow{" + "ranges=" + ranges  +'}';
    }
     
    int verify(int n){
        if(ranges.stream().filter(r -> r.left() >0 && r.left()<= n).count() >0){
            return ranges.stream()
                    .mapToInt(r -> r.left())
                    .filter(left -> left > 0)
                    .findFirst()
                    .getAsInt() - 1;
        }        
        return -1;
    }
}
