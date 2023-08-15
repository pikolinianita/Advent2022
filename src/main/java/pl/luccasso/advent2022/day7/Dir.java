/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day7;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class Dir implements FSNode{
    FSNode root;
    List<FSNode> children;
    private final String name;

    @Override
    public int size() {
        System.out.println("size: " + name);
        return children.stream().mapToInt(node -> node.size()).sum();
    }
    
    @Override
    public String getName() {
        return name;
    }

    public Dir(FSNode root, String name) {
        this.root = root;
        this.name = name;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Dir{" + "name=" + name + " " + children+'}';
    }

    @Override
    public FSNode up() {
        return root;
    }

    @Override
    public FSNode enter(String name) {
      return children.stream()
                .filter(node -> node.getName().equals(name))
                .filter(node -> node instanceof Dir)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("problem with node " + name));
    }

    @Override
    public void add(FSNode node) {
       children.add(node);
    }

}
