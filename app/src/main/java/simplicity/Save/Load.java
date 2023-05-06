package simplicity.Save;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import simplicity.Manager.Manager;

public class Load {
    public static void load(String path) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));
            SavedData saveData = gson.fromJson(reader, SavedData.class);
            System.out.println("File load successful " + path);
            Manager.setCurrentSim(saveData.getCurrentSim());
            Manager.setSimList(saveData.getSimList());
            Manager.setWorld(saveData.getWorld());
            Manager.setGameStarted(true);
            Manager.setGameLoaded(true);
        }
        catch (Exception e) {
            System.out.println("Failed to load file " + path + ".\nError message: " + e.getMessage());
        }
    }
}
