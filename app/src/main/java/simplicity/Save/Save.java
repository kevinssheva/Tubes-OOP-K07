package simplicity.Save;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import simplicity.App;
import simplicity.Inventory.Inventory;
import simplicity.Manager.Manager;

public class Save {
    public static void save() {
        String fileName = "data/data.json";
        Path path = Paths.get(fileName);
        SavedData data = new SavedData(Manager.getCurrentSim(), Manager.getSimList(), Manager.getWorld(), App.timeNow);

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            
            Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
            JsonElement tree = gson.toJsonTree(data);
            gson.toJson(tree, writer);
            System.out.println("Yeay! Berhasil melakukan saving");
        } catch (Exception e) {
            System.out.println("Yah! Gagal melakukan save. Error: " + e.getMessage());
        }
    }
}
