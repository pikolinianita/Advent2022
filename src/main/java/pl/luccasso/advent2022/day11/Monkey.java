/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day11;

import java.util.List;
import java.util.function.Function;


/**
 *
 * @author Nauczyciel
 */
public class Monkey {
    long name;
    List<Long> items;
    Function<Long,Long> worry;
    long divider;
    int sendIfTrue;
    int sendIfFalse;
    long business;
    List<Monkey> troop;
    
    public Monkey( long name, List<Long> items, Function<Long,Long> worry, long divider, long sendIfTrue, long sendIfFalse) {
        this.name = name;
        this.items = items;
        this.divider = divider;
        this.sendIfTrue = (int)sendIfTrue;
        this.sendIfFalse = (int)sendIfFalse;
        this.worry = worry;
    }

    public void addTroop(List<Monkey> troop){
        this.troop = troop;
    } 
    
    @Override
    public String toString() {
        return "Monkey{" + "name=" + name + ", items=" + items + ", worry=" + worry + ", divider=" + divider + ", sendIfTrue=" + sendIfTrue + ", sendIfFalse=" + sendIfFalse + ", business=" + business + '}';
    }
    
    void inspect(){
        while(!items.isEmpty()){
            var item = items.remove(0);
            item = worry.apply(item);
            if (item % divider == 0){
               //  System.out.println(sendIfTrue + "  " + item);
                troop.get(sendIfTrue).give(item);
            } else {
            //    System.out.println(sendIfFalse + "  " + item);
                troop.get(sendIfFalse).give(item);
            }
            business++;
        }
    }

    private void give(long item) {
        items.add(item);
    }
    
}
