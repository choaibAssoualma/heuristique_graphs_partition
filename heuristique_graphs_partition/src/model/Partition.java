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
public class Partition {
    
    private List<AbstractEnsemble> ensembles;
    
    public Partition() {
        ensembles = new ArrayList<>();
    }
    
    public void add(AbstractEnsemble e) {
        ensembles.add(e);
    }

    public void remove(AbstractEnsemble e) {
        ensembles.remove(e);
    }
    
    public List<AbstractEnsemble> getParition() {
        return ensembles;
    }
    
    @Override
    public String toString() {
        
        String sommetsLies = "\n";
        
        for (AbstractEnsemble e : ensembles) {
            sommetsLies += e.toString() + "\n";
        }
        
        return "{" + sommetsLies + "}";
    }
    
    public float ratio() {
        float ratio = 0.0f;
        
        for (AbstractEnsemble e : ensembles) {
            int poids = e.getPoids();
            
            if (poids == 0) {
                ratio = Float.MAX_VALUE;
                break;
            }
            ratio += (float) e.coutCoupeTotal() / e.getPoids();
        }
        
        return ratio;
    }
    
    public float NCut() {
        float ratio = 0.0f;
        
        for (AbstractEnsemble e : ensembles) {
            int coupe = e.coutCoupeTotal();
            ratio += (float) coupe / (e.getPoids() + coupe);
        }
        
        return ratio;
    }
    
}

