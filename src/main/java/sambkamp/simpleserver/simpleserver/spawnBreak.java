package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class spawnBreak implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        int blockX = e.getBlock().getLocation().getBlockX();
        int blockY = e.getBlock().getLocation().getBlockY();
        int blockZ = e.getBlock().getLocation().getBlockZ();
        if (blockX >= 120 && blockX <= 161 && blockZ <= -113 && blockZ >= -153){
            if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "you can't break spawn, dickhead");
            }
        } else if (e.getBlock().hasMetadata("heh")){
            List<MetadataValue> meta = e.getBlock().getMetadata("heh");
            StringBuffer sb = new StringBuffer("");
            String stringFromTheArrow;
            for (MetadataValue value : meta){
                stringFromTheArrow = value.asString();
                sb.append(stringFromTheArrow);
            }

            if (e.getPlayer().getUniqueId().toString().equals(sb.toString())){
                return;
            }

            e.getPlayer().sendMessage(ChatColor.RED + "you can't break this shop sicko");
            e.setCancelled(true);
        }
    }
    public class dontExplodeSpawn implements Listener {
        @EventHandler
        public void onExplode(EntityExplodeEvent e){
            int blockX = e.getLocation().getBlockX();
            int blockY = e.getLocation().getBlockY();
            int blockZ = e.getLocation().getBlockZ();

            if (blockX >= 120 && blockX <= 161 && blockZ <= -113 && blockZ >= -153){
                e.setCancelled(true);
            }else if (e.getLocation().getBlock().hasMetadata("heh")){
                e.setCancelled(true);
            }
        }
    }
}
