package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class signColor implements Listener {
    SimpleServer plugin;

    public signColor(SimpleServer simpleserver) {
        this.plugin = simpleserver;
    }

    @EventHandler
    public void signChange(SignChangeEvent e){
        if (e.getBlock().getType().toString().endsWith("_SIGN")){
            String amp = e.getLine(0); //sign parsing stuff
            if (!amp.equals("[Trade]")) return;
            e.setLine(0, ChatColor.LIGHT_PURPLE + e.getLine(0));
            e.setLine(3, "0/0");
            e.getPlayer().sendMessage(ChatColor.AQUA + "Shop set!");
            shopBuild shop = new shopBuild(plugin);
            e.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));
            shop.toJson(e.getPlayer().getUniqueId().toString(), e.getBlock().getZ(), e.getBlock().getX(), e.getBlock().getY());
            try {
                String contents = new String(Files.readAllBytes(Paths.get("SimpleServer/conf.txt")));
                String jsonText = contents + e.getPlayer().getUniqueId() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ() + "\n";

                //TODO: make this more efficient by not creating a new file every time.

                BufferedWriter writer = new BufferedWriter(new FileWriter("SimpleServer/conf.txt"));
                writer.write(jsonText);
                writer.close();

            }catch(Exception I_AM_GAY) {
                System.out.print("[SimpleServer] something went wrong while reading from players.txt: " + I_AM_GAY);
            }
        }
    }
}
