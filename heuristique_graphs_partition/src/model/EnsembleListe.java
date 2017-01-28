/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Implémentation des ensembles via l'utilisation de liste
 * Une autre implémentation pourrait utiliser des HashMap...
 * 
 * @author remi
 */
public class EnsembleListe extends AbstractEnsemble {
    
    /**
     * cut(E, c)   si c n'appartient pas à l'ensemble courant
     * poids(c)    sinon
     * 
     * peut être vu comme le cout de la coupe entre l'ensemble courant et tous les sommets voisins
     * sinon comme le poids du sommet au sein de l'ensemble courant
     */
    private int[] couts;
    
    /**
     * false    si un sommet n'est pas dans l'ensemble
     * true     sinon
     */
    private boolean[] dedans;
    
    public EnsembleListe(int maxSommets) {
        super();
        couts = new int[maxSommets];
        dedans = new boolean[maxSommets];
    }
    
    /**
     * 
     * 
     * @param s 
     */
    @Override
    public void add(Sommet s) {
        int sId = s.getId();
        
        if (!dedans[sId]) {
            sommets.add(s);
       
            // le cout d'un sommet à ajouter dans l'ensemble représente le cout de la coupe entre le sommet et l'ensemble courant
            // il suffit alors d'ajouter son cout au poids actuel pour obtenir le nouveau poids
            poids += couts[sId];
            coupeTotal -= couts[sId];
            
            dedans[sId] = true;

            // chaque arrete augmente le cout de la coupe si le sommet de destination ne se trouve pas dans le graphe
            // il augmente le poids du sommet sinon
            for (Arete a : s.getAretes()) {
                int dId = a.getDestination().getId();
                couts[dId]++;
                
                if (!dedans[dId]) {
                    coupeTotal++;
                }
            }
        }
        
    }
    
    @Override
    public void remove(Sommet s) {
        int sId = s.getId();
        
        if (dedans[sId]) {
            sommets.remove(s);
            
            // le cout d'un sommet représente alors le poids du sommet dans l'ensemble courant
            poids -= couts[sId];
            coupeTotal += couts[sId];
            
            dedans[sId] = false;
            
            // puisque le sommet est enlevé, il faut répercuter le retrait sur le cout de tous les sommets
            for (Arete a : s.getAretes()) {
                int dId = a.getDestination().getId();
                couts[dId]--;
                
                if (!dedans[dId]) {
                    coupeTotal--;
                }
            }
            
        }
    }
    
    /**
     * Somme du cout des sommets contenu dans l'ensemble e
     * Puisque le tableau couts contient toutes les coupes réalisées entre l'ensemble courant et les sommets
     * 
     * @param e
     * @return 
     */
    // pour se la pété un peu...
    @Override
    public int coutCoupe(AbstractEnsemble e) {
        return e.getSommets().stream().map((s) -> couts[s.getId()]).reduce(0, Integer::sum);
    }
    
    /**
     * quand la coupe est maximale on peut dire que...
     * "La coupe est pleine !"
     * (•_•) ( •_•)>⌐■-■ (⌐■_■)
     * @return 
     */
    @Override
    public int coutCoupeTotal() {
        return coupeTotal;
    }  
    
}
