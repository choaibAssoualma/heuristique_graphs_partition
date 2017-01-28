/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ibi
 */
public class Graphe {
    
    private int nbSommets;
    private int nbAretes;
    private String location;
    
    private List<Sommet> sommets;
    private List<Partition> partitions;
    
    public Graphe(String location) {
        this.sommets = new ArrayList<Sommet>();
        this.partitions = new ArrayList<Partition>();
        this.location = location;
    }
    
    /**
     * Renvoie le sommet ayant l'ID spécifié.
     * @param id
     * @return le sommet trouvé, null sinon
     */
    public Sommet getSommet(int id) {
        Sommet sommet = null;
        
          for (Sommet s : this.sommets) {
            if (s.getId() == id) {
                sommet = s;
                break;
            }
        }
        
        return sommet;
    }
    
    /**
     * Renvoie le sommet d'ID spécifié. Le crée s'il n'existe pas.
     * @param id
     * @return 
     */
    public Sommet ajouterSommet(int id) {
        Sommet sommet = this.getSommet(id);
        // si le sommet courant n'existe pas encore alors le créer
        if (sommet == null) {
           sommet = new Sommet(id);
           this.sommets.add(sommet);
        } 

        return sommet;
    }
    
    public boolean sommetExiste(int id) {
        return this.getSommet(id) != null;
    }
    
    public List<Sommet> getSommets() {
        return sommets;
    }
    
    public int size() {
        return sommets.size();
    }
    
    @Override
    public String toString() {
        return "Graphe{" + "nbSommets=" + nbSommets + ", nbAretes=" + nbAretes + ", location=" + location + ", sommets=\n" + sommets + ", partitions=" + partitions + '}';
    }
    
    // c'est bo
    // je me suis permis de faire commencer le sommet à 0 
    // sinon je devais réaliser des i-1 dans tous les algos
    public void chargerGraphe() throws FileNotFoundException {
        Scanner scFichier = new Scanner(new File(this.location));
        
        // récupérer le nombre de sommets et d'arêtes du graphe 
        Scanner scInfos = new Scanner(scFichier.nextLine());
        this.nbSommets = scInfos.nextInt();
        this.nbAretes = scInfos.nextInt();  
        scInfos.close();
        
        int idSommetCourant = 0;
        // parcourir tous les sommets (lignes)
        while (scFichier.hasNextLine()) {
            
            Sommet sommetCourant = this.ajouterSommet(idSommetCourant);
            
            Scanner scSommetsLies = new Scanner(scFichier.nextLine());
            // ajouter tous les sommets liés au sommet courant
            while (scSommetsLies.hasNextInt()) {
                Sommet sommetLie = this.ajouterSommet(scSommetsLies.nextInt()-1);
                sommetCourant.ajouterArete(sommetLie);
            }
            scSommetsLies.close();
            
            idSommetCourant++;
        }
        
        scFichier.close();
        
        System.out.println("Graphe charge (" + this.nbSommets + " sommets, " + this.nbAretes + " aretes)");
    }
    
    public Partition parGlouton(int init) {
        Partition partition = new Partition();
        
        AbstractEnsemble ens = new EnsembleListe(size());
        partition.add(ens);
        
        float cout;
        float min = Float.MAX_VALUE;
        int nbSommet = 0;
        int i = init;
        
        while (nbSommet < sommets.size()) {
            ens.add(sommets.get(i));
            cout = (float) ens.coutCoupeTotal() / (ens.getPoids() + ens.coutCoupeTotal());
            
            if (cout < min) {
                min = cout;    
            } else {
                min = Float.MAX_VALUE;
                
                // pas très optimal tout ça...
                ens.remove(sommets.get(i));
                ens = new EnsembleListe(size());
                ens.add(sommets.get(i));
                
                partition.add(ens);
            }
            
            // permet de commencer à un sommet supérieur à 0
            // pour se la pété encore...
            i = (i == sommets.size()-1) ? 0 : i+1;
            nbSommet++;
        }
        
        return partition;
    }
    
}
