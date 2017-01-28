/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ibi
 */
public class Arete {
    
    private Sommet origine;
    private Sommet destination;
    
    public Arete(Sommet origine, Sommet destination) {
        this.origine = origine;
        this.destination = destination;
    }

    public Sommet getOrigine() {
        return origine;
    }

    public Sommet getDestination() {
        return destination;
    }
    
    @Override
    public String toString() {
        return "Arete{" + "origine=" + origine.getId() + ", destination=" + destination.getId() + '}';
    }
    
}
