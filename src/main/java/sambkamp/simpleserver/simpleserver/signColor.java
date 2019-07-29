package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class signColor implements Listener {
    @EventHandler
    public void signChange(SignChangeEvent e){
        if (e.getBlock().getType() == Material.OAK_WALL_SIGN || e.getBlock().getType() == Material.OAK_SIGN){
            String amp[] = e.getLine(0).split(" - "); //sign parsing stuff
            if (!amp[0].equals("[T]")) return;
            e.setLine(0, ChatColor.LIGHT_PURPLE + e.getLine(0));
            e.getPlayer().sendMessage(ChatColor.AQUA + "Shop set!");
        }
    }
}
