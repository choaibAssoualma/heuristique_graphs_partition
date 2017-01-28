/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ibi
 */
public class Sommet {
    
    private int id;
    private List<Arete> aretes;
    
    public Sommet(int id) {
        this.id = id;
        this.aretes = new ArrayList<Arete>();
    }
    
    public void ajouterArete(Sommet destination) {
        this.aretes.add(new Arete(this, destination));
    }

    public int getId() {
        return id;
    }

    public List<Arete> getAretes() {
        return aretes;
    }

    @Override
    public String toString() {
        
        String sommetsLies = "";
        
        for (Arete a : this.aretes) {
            sommetsLies += a.getDestination().getId() + ", ";
        }
        
        return "Sommet {" + "id=" + id + ", aretes=[" + sommetsLies + "]}\n";
    }
    
}
