package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class signColor implements Listener {
    @EventHandler
    public void signChange(SignChangeEvent e){
        if (e.getBlock().getType().equals(Material.OAK_WALL_SIGN) || e.getBlock().getType().equals(Material.OAK_SIGN)){
            Sign sign = (Sign) e.getBlock().getState();
            String amp[] = sign.getLine(0).split(" - ");
            e.getPlayer().sendMessage(sign.getLine(0));
            if (!amp[0].equals("[T]")){
                return;
            }
            sign.setLine(0, ChatColor.LIGHT_PURPLE + sign.getLine(0));
            sign.update();
            e.getPlayer().sendMessage(ChatColor.AQUA + "Shop set!");
            return;
        }
    }
}
