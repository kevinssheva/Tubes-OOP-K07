package simplicity.Save;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import simplicity.Manager.Manager;
import simplicity.Sim.Sim;

public class Load {
    public static void load(String path) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));
            SavedData saveData = gson.fromJson(reader, SavedData.class);
            System.out.println("Yeay! Berhasil melakukan load file " + path);
            Manager.setSimList(saveData.getSimList());
            Manager.setWorld(saveData.getWorld());
            Manager.setGameStarted(true);
            Manager.setGameLoaded(true);
            Manager.updateSimInventory();
            Manager.setCurrentSim(Manager.getSimList().get(0));
        }
        catch (Exception e) {
            System.out.println("Yah! Gagal melakukan load file " + path + ". Error: " + e.getMessage());
        }
    }
}
