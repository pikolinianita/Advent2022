/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.luccasso.advent2022.day7;

import java.util.List;

/**
 *
 * @author Teresa
 */
public class Fil implements FSNode {
    FSNode root;
    List<FSNode> children;
    String name;
    int size;
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    public Fil(FSNode root, String name, int size) {
        this.root = root;
        this.name = name;
        this.size = size;
    }
    
    public Fil(FSNode root, String line) {
        this.root = root;
        var splitted = line.split(" ");
        this.name = splitted[1];
        this.size = Integer.valueOf(splitted[0]);
    }

    @Override
    public String toString() {
        return "Fil{" + "name=" + name + ", size=" + size + '}';
    }

    @Override
    public FSNode up() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FSNode enterDir(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(FSNode node) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
