package simplicity.Save;

import java.util.*;

import simplicity.Sim.Sim;
import simplicity.World.World;

public class SavedData {
    private Sim currentSim;
    private List<Sim> simList;
    private World world;

    public SavedData(Sim currentSim, List<Sim> simList, World world) {
        this.currentSim = currentSim;
        this.simList = simList;
        this.world = world;
    }

    public Sim getCurrentSim() {
        return currentSim;
    }

    public List<Sim> getSimList() {
        return simList;
    }

    public World getWorld() {
        return world;
    }
}
