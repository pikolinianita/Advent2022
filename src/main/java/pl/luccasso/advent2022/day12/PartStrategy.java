/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.luccasso.advent2022.day12;

/**
 *
 * @author Teresa
 */
public interface PartStrategy {
    void qeueuSetup();
    boolean hasEnded(Position pos);
    boolean isReachable(Position current, Position next);
}
