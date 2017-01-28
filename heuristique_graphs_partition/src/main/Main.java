/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Graphe;
import model.Partition;

/**
 *
 * @author remi
 */
public class Main {
    
    public static void main(String [] args) {
        Graphe g = new Graphe("/home/remi/dev/NetBeansProjects/5il-oc-partitionnement-graphe/5il-oc-partitionnement-graphe/resources/exemple.graphe");
        
        try {
            g.chargerGraphe();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graphe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0 ; i < g.size() ; i++) {
            long startTime = System.currentTimeMillis();
            Partition p = g.parGlouton(i);
            long stopTime = System.currentTimeMillis();
            System.out.println("Sommet = " + i + "\tt = " + (stopTime - startTime));
            System.out.println(p);
        }
       
    }
    
}
