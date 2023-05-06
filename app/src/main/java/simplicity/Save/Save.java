package simplicity.Save;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import simplicity.Inventory.Inventory;
import simplicity.Manager.Manager;

public class Save {
    public static void save() {
        String fileName = "data/data.json";
        Path path = Paths.get(fileName);
        SavedData data = new SavedData(Manager.getCurrentSim(), Manager.getSimList(), Manager.getWorld());

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            
            Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
            JsonElement tree = gson.toJsonTree(data);
            gson.toJson(tree, writer);
            System.out.println("Berhasil melakukan save.");// Gson gson = new Gson();
            // gson.toJson(sim, writer);
            // System.out.println("Berhasil melakukan save.");
        } catch (Exception e) {
            System.out.println("Gagal melakukan save. Pesan error: " + e.getMessage());
        }
    }
}
