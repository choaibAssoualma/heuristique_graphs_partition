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
public abstract class AbstractEnsemble {
    
    // a remplacer a terme par une liste de sId
    protected List<Sommet> sommets;
    protected int poids;
    protected int coupeTotal;
    
    public AbstractEnsemble() {
        sommets = new ArrayList<>();
        poids = 0;
        coupeTotal = 0;
    }
    
    public int getPoids() {
        return poids;
    }
    
    public List<Sommet> getSommets() {
        return sommets;
    }
    
    @Override
    public String toString() {
        
        String sommetsLies = "";
        
        for (Sommet s : sommets) {
            sommetsLies += s.getId() + ", ";
        }
        
        return "{" + sommetsLies + "}";
    }
    
    public abstract void add(Sommet s);
    
    public abstract void remove(Sommet s);
    
    public abstract int coutCoupe(AbstractEnsemble e);
    
    public abstract int coutCoupeTotal();
    
}
