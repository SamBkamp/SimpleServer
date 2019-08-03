package sambkamp.simpleserver.simpleserver;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

public class spawnBreak implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        int blockX = e.getBlock().getLocation().getBlockX();
        int blockY = e.getBlock().getLocation().getBlockY();
        int blockZ = e.getBlock().getLocation().getBlockZ();
        if (blockX >= 120 && blockX <= 161 && blockZ <= -113 && blockZ >= -153) {
            if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "you can't break spawn");
            }
        } else if (e.getBlock().getType().equals(Material.OAK_SIGN) || e.getBlock().getType().equals(Material.OAK_WALL_SIGN)) {
            //Block sign  = e.getBlock();
            Sign sign = (Sign) e.getBlock().getState();
            String firstLine = ChatColor.stripColor(sign.getLine(0));

            if (!firstLine.equalsIgnoreCase("[Trade]")) {
                return;
            }
            String firstItem = sign.getLine(1).split(", ")[0];
            String secondItem = sign.getLine(2).split(", ")[0];
            int firstItemAmount = Integer.parseInt(sign.getLine(3).split("/")[0]);
            int secondItemAmount = Integer.parseInt(sign.getLine(3).split("/")[1]);
            if (firstItemAmount > 0) {
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.valueOf(firstItem), firstItemAmount));
            }
            if (secondItemAmount > 0) {
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.valueOf(secondItem), secondItemAmount));
            }
        } else if (e.getBlock().hasMetadata("heh")) {
            List<MetadataValue> meta = e.getBlock().getMetadata("heh");
            StringBuffer sb = new StringBuffer("");
            String stringFromTheArrow;
            for (MetadataValue value : meta) {
                stringFromTheArrow = value.asString();
                sb.append(stringFromTheArrow);
            }

            if (e.getPlayer().getUniqueId().toString().equals(sb.toString())) {
                return;
            }

            e.getPlayer().sendMessage(ChatColor.RED + "you can't break this shop");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        int blockX = e.getLocation().getBlockX();
        int blockY = e.getLocation().getBlockY();
        int blockZ = e.getLocation().getBlockZ();
        Block getblock = e.getLocation().getBlock();
        ArrayList<Block> fenceBlocks = new ArrayList<>();

        for (int i=0; i < e.blockList().size(); i++){
            if (e.blockList().get(i).getType().equals(Material.OAK_SIGN) || e.blockList().get(i).getType().equals(Material.OAK_WALL_SIGN)){
                fenceBlocks.add(e.blockList().get(i));
            }
        }

        if (blockX >= 120 && blockX <= 161 && blockZ <= -113 && blockZ >= -153) {
            e.setCancelled(true);
        } else if (e.getLocation().getBlock().hasMetadata("heh")) {
            e.setCancelled(true);
        } else if (!fenceBlocks.isEmpty()) {
            for (int i = 0; i <fenceBlocks.size(); i++){
                Sign sign = (Sign) fenceBlocks.get(i).getState();
                if (sign.getLine(0).contains("[T]")) {
                    String firstItem = sign.getLine(1).split(", ")[0];
                    String secondItem = sign.getLine(2).split(", ")[0];
                    int firstItemAmount = Integer.parseInt(sign.getLine(3).split("/")[0]);
                    int secondItemAmount = Integer.parseInt(sign.getLine(3).split("/")[1]);
                    fenceBlocks.get(i).getWorld().dropItem(fenceBlocks.get(i).getLocation(), new ItemStack(Material.valueOf(firstItem), firstItemAmount));
                    fenceBlocks.get(i).getWorld().dropItem(fenceBlocks.get(i).getLocation(), new ItemStack(Material.valueOf(secondItem), secondItemAmount));
                }
            }
        }
    }
}
