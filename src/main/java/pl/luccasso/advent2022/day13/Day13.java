package pl.luccasso.advent2022.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author piko
 */
interface Node {

    public int size();

    public Node append(Node node);

    public Iterator<? extends Node> iterator();

}

class NodeList implements Node {

    Queue<Node> nodeList;

    public NodeList() {
        nodeList = new LinkedList<>();
    }

    @Override
    public String toString() {
        return nodeList.toString();
    }

    @Override
    public Node append(Node node) {
        nodeList.add(node);
        return this;
    }

    @Override
    public int size() {
        return nodeList.size();
    }

    @Override
    public Iterator<Node> iterator() {
        return nodeList.iterator();
    }

}

class NodeNumber implements Node {

    int number;

    public NodeNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int size() {
        return -1;
    }

    @Override
    public Node append(Node node) {
        throw new IllegalStateException("Should no appand to number!!!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<NodeNumber> iterator() {
        return List.of(this).iterator();
    }
}

class NodeComparator implements Comparator<Node> {
//a negative integer, zero, or a positive integer as the 
//first argument is less than, equal to, or greater than the second.

    @Override
    public int compare(Node o1, Node o2) {
        //if two numbers - compare them
        if ((o1.size() == -1) && (o2.size() == -1)) {
            return ((NodeNumber) o1).number - ((NodeNumber) o2).number;
        } else //if two lists - compare each pair of elements
        if ((o1.size() > -1) && (o2.size() > -1)) {
            var i1 = o1.iterator();
            var i2 = o2.iterator();
            while (i1.hasNext() && i2.hasNext()) {
                var result = this.compare(i1.next(), i2.next());
                if (result != 0) {
                    return result;
                }
            }
            //but if all first elements are equal => chance that one list have more elements
            return o1.size() - o2.size();
        } //o1 number, o2 list
        else if (o1.size() == -1) {
            return compare(new NodeList().append(o1), o2);
        } //o1 list, o2 number
        else if (o2.size() == -1) {
           return compare(o1, new NodeList().append(o2));
        }

        throw new RuntimeException("should not happen");

    }

}

class PacketBuilder {

    static Node createPacket(String line) {
        var iterator = Arrays.asList(line.split("")).iterator();
        iterator.next();
        var result = createNode(iterator);

        return result;
    }

    private static Node createNode(Iterator<String> iterator) {

        String temporaryNumber = "";
        String sign;
        Node result = new NodeList();
        while (iterator.hasNext()) {
            sign = iterator.next();
//            System.out.println("sign: " + sign);
//            System.out.println("result: " + result);
            switch (sign) {
                case "1", "2", "3","4","5", "6","7","8","9","0":
                    temporaryNumber += sign;
                    break;
                case ",":
                    if (!"".equals(temporaryNumber)) {
                        result.append(new NodeNumber(Integer.parseInt(temporaryNumber)));
                        temporaryNumber = "";
                    }
                    break;

                case "[":
                    result.append(createNode(iterator));
                    break;
                case "]":
                    if (!"".equals(temporaryNumber)) {
                        result.append(new NodeNumber(Integer.parseInt(temporaryNumber)));
                    }
                    return result;
            }
        }
        System.out.println(result);
        System.out.println(iterator.toString());
        throw new RuntimeException("Parser fail!!!");
    }

}

public class Day13 {

   private final List<String> lines;
   private final Comparator<Node> comparator;
    
    public int calculateP1(){
        int counter = 1;
        var iter = lines.iterator();
        int result = 0;
        while (iter.hasNext()){
            if (0 > comparator.compare(PacketBuilder.createPacket(iter.next()),
                                       PacketBuilder.createPacket(iter.next()))){
                result += counter;
            }
            counter++;
        }
        return result;
    }
    
    public int calculateP2(){
       var list = lines.stream()
                .map(PacketBuilder::createPacket)
                .collect(Collectors.toCollection(LinkedList::new));
       var divider6 =  PacketBuilder.createPacket("[[6]]");
       var divider2 =  PacketBuilder.createPacket("[[2]]");
       list.add(divider2);
       list.add(divider6);
       list.sort(comparator);
       
       return (list.indexOf(divider2) +1 ) * (list.indexOf(divider6) +1 );
    }
    
     public Day13(List<String> lines) {
         this.lines = lines;
        this.comparator = new NodeComparator();
    }
     
    public static void main(String[] args) throws FileNotFoundException {
       var day = new Day13 ( new Scanner(new File("day13.txt")).tokens().toList());
       System.out.println("day13 part 1 result is: " + day.calculateP1());
       System.out.println("day13 part 2 result is: " + day.calculateP2());
       
    }
    
}
