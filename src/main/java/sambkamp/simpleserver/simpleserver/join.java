package sambkamp.simpleserver.simpleserver;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.AQUA + "Welcome " + event.getPlayer().getName() + ", read the rules at /helpme");
        event.getPlayer().chat("/msg");
        try {
            String iterator[] = getName().split("\n");

            for (int i = 0; i < iterator.length; i++){
                if (iterator[i].equals(event.getPlayer().getUniqueId().toString())){
                    return;
                }
            }
            setName(event.getPlayer().getUniqueId().toString());
            ItemStack itemStack = new ItemStack(Material.OAK_FENCE, 1);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "shop builder");
            PlayerInventory inventory = event.getPlayer().getInventory();

            itemStack.setItemMeta(meta);
            inventory.addItem(itemStack);

        }catch (Exception HEH){
            System.out.println("something went wrong with getName() in join.java: " + HEH);
        }

    }

    public void setName(String uuid) {


        try {
            String contents = new String(Files.readAllBytes(Paths.get("SimpleServer/players.txt")));
            String jsonText = contents + uuid + "\n";

            //TODO: make this more efficient by not creating a new file every time. See shopBuild.java (290,15)

            BufferedWriter writer = new BufferedWriter(new FileWriter("SimpleServer/players.txt"));
            writer.write(jsonText);
            writer.close();

        }catch(Exception I_AM_GAY) {
            System.out.print("[SimpleServer] something went wrong while reading from players.txt: " + I_AM_GAY);
        }


    }
    public String getName() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("SimpleServer/players.txt")));
        return contents;
    }

}