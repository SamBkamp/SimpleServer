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
        System.out.println("[SimpleServer] plug-in started");
        getServer().getPluginManager().registerEvents(join, this);
        getServer().getPluginManager().registerEvents(new shopBuild(this), this);
        getServer().getPluginManager().registerEvents(new spawnBreak(), this);
        getServer().getPluginManager().registerEvents(new signs(), this);
        getServer().getPluginManager().registerEvents(new signColor(this), this);
        System.out.println("[SimpleServer] listeners loaded");
        this.getCommand("helpme").setExecutor(commandClass);
        this.getCommand("makeshop").setExecutor(commandClass);
        this.getCommand("sellids").setExecutor(commandClass);
        this.getCommand("credits").setExecutor(commandClass);
        this.getCommand("spawn").setExecutor(commandClass);

        Path path =  Paths.get("SimpleServer/");
        Path file = Paths.get("SimpleServer/conf.txt");
        Path players = Paths.get("SimpleServer/players.txt");

        if (Files.exists(path)) {
            System.out.println("[SimpleServer] Already found SimpleServer folder, skipping...");
            try {
                setupMeta();
            }catch (Exception AMP){
                System.out.println("[SimpleServer] Error while loading metadata: " + AMP);
            }
            return;
        }else {

            boolean success = (new File("SimpleServer")).mkdirs();
            if (!success) {
                System.out.println("[SimpleServer] Could not create SimpleServer folder. some functionality will be lost.");
            } else {

                if (Files.exists(file)) {
                    System.out.println("[SimpleServer] Already found conf.txt, skipping...");

                }else {
                    try {
                        PrintWriter writer = new PrintWriter("SimpleServer/conf.txt", "UTF-8");
                        writer.close();
                        System.out.println("[SimpleServer] created SimpleServer/conf.txt");
                    } catch (Exception e) {
                        System.out.println("[SimpleServer] Could not create conf.txt: " + e);
                    }
                }
                if (Files.exists(players)) {
                    System.out.println("[SimpleServer] Already found players.txt, skipping...");

                }else {
                    try {
                        PrintWriter writer = new PrintWriter("SimpleServer/players.txt", "UTF-8");
                        writer.close();
                        System.out.println("[SimpleServer] created SimpleServer/players.txt");
                    } catch (Exception e) {
                        System.out.println("[SimpleServer] Could not create players.txt: " + e);
                    }
                }

            }
        }
        System.out.println("[SimpleServer] Fully loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[SimpleServer] plugin exiting");
    }

    public void setupMeta() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("SimpleServer/conf.txt")));
        if (contents.equals("")){
            System.out.println("[SimpleServer] conf.txt is empty, skipping...");
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