/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

/**
 *
 * @author T-Money
 */
class Teams {

   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
    
    private String team;
    private String name;
    private double atBats;
    private int hits;

    //default constructor
    public Teams () {
        this.team = "";
        this.name = "";
        this.atBats = 0;
        this.hits = 0;
    }

 
    public Teams (String team, String name, double atBats, int hits) {
        this.team = team;
        this.name = name;
        this.atBats = atBats;
    }
    
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName () {
        return name;
    }

    public void setName(String playername) {
        this.name = playername;
    }

    public double getAtBats() {
        return atBats;
    }

    public void setAtBats(double atBats) {
        this.atBats = atBats;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

   
    @Override
    public String toString(){
        return team;
    }
    
    
}

    
