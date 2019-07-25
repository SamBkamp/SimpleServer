package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class shopBuild implements Listener {

    SimpleServer plugin;

    public shopBuild(SimpleServer simpleserver) {
        this.plugin = simpleserver;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "shop builder")) {
            //TODO: make this more efficient (less for loops/less lines of code)
            //fence posts
            for (int i = 0; i < 4; i++) {
                Location block = e.getBlock().getLocation().add(0, i, 0);
                block.getBlock().setType(Material.OAK_FENCE);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location topBlock = e.getBlock().getLocation().add(-8, i, 0);
                topBlock.getBlock().setType(Material.OAK_FENCE);
                topBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));


                try {//Idk why this is in a try catch block either - Bkamp_
                    toJson(e.getPlayer().getUniqueId().toString(), block.getBlockZ(), block.getBlockX(), block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), topBlock.getBlockZ(), topBlock.getBlockX(), topBlock.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }

            }

            //TODO: make variable names less stupid
            //going across
            for (int i = 1; i < 8; i ++){
                Location block = e.getBlock().getLocation().add(0, 0, (i*-1)-1);
                block.getBlock().setType(Material.SPRUCE_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location topBlock = e.getBlock().getLocation().add(0, 3, (i*-1)-1);
                topBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                topBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location sideBlock = e.getBlock().getLocation().add(-8, 0, (i*-1)-1);
                sideBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                sideBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location otherSideBlock = e.getBlock().getLocation().add(-8, 3, (i*-1)-1);
                otherSideBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                otherSideBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString(), block.getBlockZ(), block.getBlockX(),block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), topBlock.getBlockZ(), topBlock.getBlockX(),topBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), sideBlock.getBlockZ(), sideBlock.getBlockX(),sideBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), otherSideBlock.getBlockZ(), otherSideBlock.getBlockX(), otherSideBlock.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }
            }
            //behind the fence posts
            for (int i = 0; i < 5; i ++){
                Location block = e.getBlock().getLocation().add(0, i, -1);
                block.getBlock().setType(Material.SPRUCE_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location topBlock = e.getBlock().getLocation().add(-8, i, -1);
                topBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                topBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location backBlock = e.getBlock().getLocation().add(0, i, -9);
                backBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                backBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location backBlockTwo = e.getBlock().getLocation().add(-8, i, -9);
                backBlockTwo.getBlock().setType(Material.SPRUCE_PLANKS);
                backBlockTwo.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString() ,block.getBlockZ(), block.getBlockX(),block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), topBlock.getBlockZ(), topBlock.getBlockX(), topBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), backBlock.getBlockZ(), topBlock.getBlockX(), topBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), backBlockTwo.getBlockZ(), topBlock.getBlockX(), topBlock.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }
            }

            //top slabs
            for (int i = 0; i < 10; i ++){
                Location block = e.getBlock().getLocation().add(0, 4, i*-1);
                block.getBlock().setType(Material.SMOOTH_STONE_SLAB);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString() ,block.getBlockZ(), block.getBlockX(),block.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }
                for (int j = 1; j < 9; j ++){
                    Location acrossBlock = block.getBlock().getLocation().add(j*-1, 0, 0);
                    acrossBlock.getBlock().setType(Material.SMOOTH_STONE_SLAB);
                    acrossBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                    try {
                        toJson(e.getPlayer().getUniqueId().toString() ,acrossBlock.getBlockZ(), acrossBlock.getBlockX(),acrossBlock.getBlockY());
                    } catch (Exception exception) {
                        e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                    }
                }
            }

            //bottom floor
            for (int i = 1; i < 10; i++){
                Location block = e.getBlock().getLocation().add(-1, -1, i*-1);
                block.getBlock().setType(Material.BIRCH_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString() ,block.getBlockZ(), block.getBlockX(),block.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }

                for (int j = 1; j < 7; j ++) {
                    Location acrossBlock = block.getBlock().getLocation().add(j * -1, 0, 0);
                    acrossBlock.getBlock().setType(Material.BIRCH_PLANKS);
                    acrossBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                    try {
                        toJson(e.getPlayer().getUniqueId().toString(), acrossBlock.getBlockZ(), acrossBlock.getBlockX(), acrossBlock.getBlockY());
                    } catch (Exception exception) {
                        e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                    }
                }
            }
            //top and bottom border
            for (int i = 1; i < 8; i++){
                Location block = e.getBlock().getLocation().add(i*-1, 3, -9);
                block.getBlock().setType(Material.SPRUCE_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location frontBlock = e.getBlock().getLocation().add(i*-1, 3, -1);
                frontBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                frontBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location bottomBlock = e.getBlock().getLocation().add(i*-1, 0, -9);
                bottomBlock.getBlock().setType(Material.SPRUCE_PLANKS);
                bottomBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString(), block.getBlockZ(), block.getBlockX(), block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), frontBlock.getBlockZ(), frontBlock.getBlockX(),frontBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), bottomBlock.getBlockZ(), bottomBlock.getBlockX(), bottomBlock.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }

            }

            //inside blocks
            for (int i = 2; i < 9; i++){
                Location block = e.getBlock().getLocation().add(-1, 0, i*-1);
                block.getBlock().setType(Material.OAK_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location frontBlock = e.getBlock().getLocation().add(-7, 0, i*-1);
                frontBlock.getBlock().setType(Material.OAK_PLANKS);
                frontBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location bottomBlock = e.getBlock().getLocation().add(-1, 2, i*-1);
                bottomBlock.getBlock().setType(Material.OAK_PLANKS);
                bottomBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location leftBlock = e.getBlock().getLocation().add(-7, 2, i*-1);
                leftBlock.getBlock().setType(Material.OAK_PLANKS);
                leftBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location glassBlock1 = e.getBlock().getLocation().add(-7, 1, i*-1);
                glassBlock1.getBlock().setType(Material.GLASS);
                glassBlock1.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location glassBlock2 = e.getBlock().getLocation().add(-1, 1, i*-1);
                glassBlock2.getBlock().setType(Material.GLASS);
                glassBlock2.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString(), block.getBlockZ(), block.getBlockX(), block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), frontBlock.getBlockZ(), frontBlock.getBlockX(),frontBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), bottomBlock.getBlockZ(), bottomBlock.getBlockX(), bottomBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), leftBlock.getBlockZ(), leftBlock.getBlockX(), leftBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), glassBlock1.getBlockZ(), glassBlock1.getBlockX(), glassBlock1.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), glassBlock2.getBlockZ(), glassBlock2.getBlockX(), glassBlock2.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }

            }

            //black inside blocks

            for (int i = 2; i < 7; i++){
                Location block = e.getBlock().getLocation().add(i*-1, 0, -8);
                block.getBlock().setType(Material.OAK_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location middleBlock = e.getBlock().getLocation().add(i*-1, 1, -8);
                middleBlock.getBlock().setType(Material.GLASS);
                middleBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location topBlock = e.getBlock().getLocation().add(i*-1, 2, -8);
                topBlock.getBlock().setType(Material.OAK_PLANKS);
                topBlock.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString(), block.getBlockZ(), block.getBlockX(), block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), middleBlock.getBlockZ(), middleBlock.getBlockX(), middleBlock.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), topBlock.getBlockZ(), topBlock.getBlockX(), topBlock.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }
            }

            //pillars af
            for (int i = 0; i < 3; i ++){
                Location block = e.getBlock().getLocation().add(-2, i, -1);
                block.getBlock().setType(Material.SPRUCE_PLANKS);
                block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location secondPillar = e.getBlock().getLocation().add(-4, i, -1);
                secondPillar.getBlock().setType(Material.SPRUCE_PLANKS);
                secondPillar.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                Location thirdPillar = e.getBlock().getLocation().add(-6, i, -1);
                thirdPillar.getBlock().setType(Material.SPRUCE_PLANKS);
                thirdPillar.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));

                try {
                    toJson(e.getPlayer().getUniqueId().toString(), block.getBlockZ(), block.getBlockX(), block.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), secondPillar.getBlockZ(), secondPillar.getBlockX(), secondPillar.getBlockY());
                    toJson(e.getPlayer().getUniqueId().toString(), thirdPillar.getBlockZ(), thirdPillar.getBlockX(), thirdPillar.getBlockY());
                } catch (Exception exception) {
                    e.getPlayer().sendMessage(ChatColor.RED + "something went wrong when saving your shop's location: " + exception);
                }
            }
            Location block = e.getBlock().getLocation().add(-4, 3, -4);
            block.getBlock().setType(Material.LANTERN);
            block.getBlock().setMetadata("heh", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId().toString()));
        }
    }

    public void toJson(String uuid, int startZ, int startX, int startY) {


        try {
            String contents = new String(Files.readAllBytes(Paths.get("SimpleServer/conf.txt")));
            String jsonText = contents + uuid + "," + startX + "," + startY + "," + startZ + "\n";

            //TODO: make this more efficient by not creating a new file every time. See join.java (54,15)

            BufferedWriter writer = new BufferedWriter(new FileWriter("SimpleServer/conf.txt"));
            writer.write(jsonText);
            writer.close();

        }catch(Exception I_AM_GAY) {
            System.out.print("[SimpleServer] something went wrong while saving the place: " + I_AM_GAY);
        }


    }
}