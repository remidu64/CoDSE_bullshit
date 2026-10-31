package remi.coDSE.data;

import org.bukkit.Location;

public class PlayerData {
    private int perk;
    private int kills;
    private boolean phase_2;
    private Location location;
    private int stopping_rounds;
    private boolean DDTrigger;

    public int getPerk() {return perk;}

    public void setPerk(final int perk) {
        this.perk = perk;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(final int kills) {
        this.kills = kills;
    }

    public void incrementKills() {
        this.kills++;
    }

    public boolean isPhase2() {return phase_2;}

    public void setPhase2(final boolean phase_2) {this.phase_2 = phase_2;}

    public Location getLocation() {return location;}

    public void setLocation(final Location location) {this.location = location;}

    public int getStopping_rounds() {return stopping_rounds;}

    public void setStopping_rounds(final int stopping_rounds) {this.stopping_rounds = stopping_rounds;}

    public void addStopping_rounds(final int stopping_rounds) {this.stopping_rounds += stopping_rounds;}

    public void decrementStopping_Rounds() {this.stopping_rounds--;}

    public boolean didDDTrigger() {return DDTrigger;}

    public void setDDTrigger(final boolean DDTrigger) {this.DDTrigger = DDTrigger;}
}
