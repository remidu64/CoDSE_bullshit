package remi.coDSE.data;

import remi.coDSE.core.CoDSE;

import java.util.List;

public class SpawnData {
    private static CoDSE plugin = CoDSE.getInstance();
    private List<List<Integer>> spawns = (List<List<Integer>>) plugin.getConfig().getList("spawns"); // safe casting ? i barely know her !

    public void ReloadSpawns() {
        this.spawns = (List<List<Integer>>) plugin.getConfig().getList("spawns");
    }

    public List<List<Integer>> getSpawns() {
        return spawns;
    }
}
