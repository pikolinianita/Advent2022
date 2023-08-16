/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.luccasso.advent2022.day7;

/**
 *
 * @author Teresa
 */
public interface FSNode {
    int size();
    String getName();
    FSNode up();
    FSNode enterDir(String name);
    void add(FSNode node);
}
