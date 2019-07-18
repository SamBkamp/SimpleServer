package sambkamp.simpleserver.simpleserver;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class SimpleServer extends JavaPlugin {

    join join = new join();
    commandClass commandClass = new commandClass();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[MCDEV] plug-in started");
        getServer().getPluginManager().registerEvents(join, this);
        getServer().getPluginManager().registerEvents(new shopBuild(this), this);
        getServer().getPluginManager().registerEvents(new spawnBreak(), this);
        getServer().getPluginManager().registerEvents(new signs(), this);
        System.out.println("[MCDEV] listeners loaded");
        this.getCommand("helpme").setExecutor(commandClass);
        this.getCommand("makeshop").setExecutor(commandClass);
        this.getCommand("sellids").setExecutor(commandClass);

        Path path =  Paths.get("MCDEV/");
        Path file = Paths.get("MCDEV/conf.txt");
        Path players = Paths.get("MCDEV/players.json");

        if (Files.exists(path)) {
            System.out.println("[MCDEV] Already found MCDEV folder, skipping...");
            try {
                setupMeta();
            }catch (Exception AMP){
                System.out.println("[MCDEV] Error while loading metadata: " + AMP);
            }
            return;
        }else {

            boolean success = (new File("MCDEV")).mkdirs();
            if (!success) {
                System.out.println("[MCDEV] Could not create MCDEV folder. some functionality will be lost.");
            } else {

                if (Files.exists(file)) {
                    System.out.println("[MCDEV] Already found conf.txt, skipping...");

                }else {
                    try {
                        PrintWriter writer = new PrintWriter("MCDEV/conf.txt", "UTF-8");
                        writer.close();
                        System.out.println("[MCDEV] created MCDEV/conf.txt");
                    } catch (Exception e) {
                        System.out.println("[MCDEV] Could not create conf.txt: " + e);
                    }
                }
                if (Files.exists(players)) {
                    System.out.println("[MCDEV] Already found players.json, skipping...");

                }else {
                    try {
                        PrintWriter writer = new PrintWriter("MCDEV/players.json", "UTF-8");
                        writer.close();
                        System.out.println("[MCDEV] created MCDEV/players.json");
                    } catch (Exception e) {
                        System.out.println("[MCDEV] Could not create players.json: " + e);
                    }
                }

            }
        }
        System.out.println("[MCDEV] Fully loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[MCDEV] plugin exiting");
    }

    public void setupMeta() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("MCDEV/conf.txt")));
        if (contents.equals("")){
            System.out.println("[MCDEV] conf.txt is empty, skipping...");
            return;
        }
        String[] iterator = contents.split("\n");
        for (int i = 0; i < iterator.length; i++){
            String coords = iterator[i];
            String[] cordio = coords.split(",");
            Block block = Bukkit.getWorld("world").getBlockAt(Integer.parseInt(cordio[1]), Integer.parseInt(cordio[2]), Integer.parseInt(cordio[3]));
            block.setMetadata("heh", new FixedMetadataValue(this, cordio[0]));
        }

    }
}