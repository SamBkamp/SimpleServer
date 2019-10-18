package sambkamp.simpleserver.simpleserver;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class SimpleServer extends JavaPlugin {

    join join = new join();
    commandClass commandClass = new commandClass(true);

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[SimpleServer] plug-in started");
        getServer().getPluginManager().registerEvents(join, this);
        getServer().getPluginManager().registerEvents(new shopBuild(this), this);
        getServer().getPluginManager().registerEvents(new spawnBreak(this), this);
        getServer().getPluginManager().registerEvents(new signs(), this);
        getServer().getPluginManager().registerEvents(new signColor(this), this);
        System.out.println("[SimpleServer] listeners loaded");
        this.getCommand("helpme").setExecutor(commandClass);
        this.getCommand("makeshop").setExecutor(commandClass);
        this.getCommand("sellids").setExecutor(commandClass);
        this.getCommand("credits").setExecutor(commandClass);
        this.getCommand("spawn").setExecutor(commandClass);
        this.getCommand("msg").setExecutor(commandClass);

        Path rootFolder =  Paths.get("SimpleServer/");

        if (Files.exists(rootFolder)) {
            System.out.println("[SimpleServer] Already found SimpleServer folder, skipping...");
            try {
                setupMeta();
            }catch (Exception AMP){
                System.out.println("[SimpleServer] Error while loading metadata: " + AMP);
            }
        }else {
            boolean success = (new File("SimpleServer")).mkdirs();
            if (!success) {
                System.out.println("[SimpleServer] Could not create SimpleServer folder. some functionality will be lost.");
                return;
            }
        }

        String[] files = {"conf.txt", "players.txt", "msg.txt"};
        for(String f : files){
            if (Files.exists(Paths.get("SimpleServer/"+f))) {
                System.out.println("[SimpleServer] Already found "+ f +", skipping...");

            }else {
                createfile("SimpleServer/"+f);
            }
        }
        try {
            if (!new String(Files.readAllBytes(Paths.get("SimpleServer/msg.txt"))).equals("")){
                String loadedMsg = new String(Files.readAllBytes(Paths.get("SimpleServer/msg.txt")));
                for (String s : loadedMsg.split("\n")){
                    commandClass.NoticeBoard.add(s);
                }//try .addAll method without foreach loop
            }
            System.out.println("[SimpleServer] Messages loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("[SimpleServer] Fully loaded");
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            FileWriter fw = new FileWriter("msg.txt");
            for (String s : commandClass.NoticeBoard){
                fw.write(s);
            }
            fw.close();
            System.out.println("[SimpleServer] Saved messages to disk");
        }catch (Exception e){
            System.out.println("[SimpleServer] Something went wrong while saving the messages to disk");
        }
        System.out.println("[SimpleServer] plugin exiting");
    }

    public void setupMeta() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("SimpleServer/conf.txt")));
        if (contents.equals("")){
            System.out.println("[SimpleServer] conf.txt is empty, skipping...");
            return;
        }
        int blocksRemoved = 0;
        String[] iterator = contents.split("\n");
        System.out.println("[SimpleServer] found " + iterator.length + " blocks");
        for (int i = 0; i < iterator.length; i++){
            String coords = iterator[i].split("\n")[0];
            String[] cordio = coords.split(",");
            int cleanlastcoord = Integer.parseInt(cordio[3].replaceAll("\\r", "")); //hacked together
            Block block = Bukkit.getWorld("world").getBlockAt(Integer.parseInt(cordio[1]), Integer.parseInt(cordio[2]), cleanlastcoord);


            if (block.getType().equals(Material.AIR)){ //deletes empty blocks from the save file to save space.
                iterator[i] = "";
                blocksRemoved++;
                continue;
            }


            block.setMetadata("heh", new FixedMetadataValue(this, cordio[0]));
        }
        System.out.println(blocksRemoved);

    }

    private boolean createfile(String fileName){
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.close();
            System.out.println("[SimpleServer] created " + fileName);
            return true;
        } catch (Exception e) {
            System.out.println("[SimpleServer] Could not create" + fileName + ": " + e);
            return false;
        }
    }


}