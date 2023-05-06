package simplicity.Save;

import java.util.*;

import simplicity.Sim.Sim;
import simplicity.World.World;

public class SavedData {
    private Sim currentSim;
    private List<Sim> simList;
    private World world;
    private long timeNow;

    public SavedData(Sim currentSim, List<Sim> simList, World world, long timeNow) {
        this.currentSim = currentSim;
        this.simList = simList;
        this.world = world;
        this.timeNow = timeNow;
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

    public long getTimeNow() {
        return timeNow;
    }
}
