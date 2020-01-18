package sambkamp.simpleserver.simpleserver;

import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.Material;

public class signs implements Listener {
    SimpleServer plugin;

    public signs(SimpleServer simpleserver) {
        this.plugin = simpleserver;
    }

    @EventHandler
    public void sign(PlayerInteractEvent e) {
        methods m = new methods();

        try {
            e.getClickedBlock().getType();
        } catch ( Exception E ) {
            return;
        }
        try {
            if (e.getItem().getType().equals(Material.STICK) && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "ShopAway\u2122")){
                if (e.getClickedBlock().hasMetadata("heh")){
                    e.getClickedBlock().removeMetadata("heh", plugin);
                    e.getPlayer().sendMessage(ChatColor.GREEN + "removed shop block");
                }
                e.setCancelled(true);
            }
        } catch (Exception ex){}

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material T = e.getClickedBlock().getType();
            if (T.toString().endsWith("STAIRS") && !e.getPlayer().isSneaking()) {
                Vector v = new Vector(0,0,0);
                Location l = new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getX() + 0.5, e.getClickedBlock().getY(), e.getClickedBlock().getZ() + 0.5);
                Arrow a = e.getClickedBlock().getWorld().spawnArrow(l, v, 0, 0);
                a.setPassenger(e.getPlayer());
            }
        }

        if (e.getClickedBlock().getType().toString().endsWith("_SIGN")) {
        } else {
            return;
        }

        try {
            Sign sign = (Sign) e.getClickedBlock().getState();
            String amp = ChatColor.stripColor(sign.getLine(0));

            if (!amp.equalsIgnoreCase("[Trade]")) {
                return;
            }
            String string = ChatColor.stripColor(sign.getLine(1));
            String[] items = string.split(", ");
            String string2 = ChatColor.stripColor(sign.getLine(2));
            String[] items1 = string2.split(", ");

            String sellItem;
            String buyItem;

            sellItem = items[0];
            buyItem = items1[0];
            //this whole alias thing is super jank but so be it
            //also it doesnt work - Bkamp


            if (!e.getClickedBlock().hasMetadata("heh")){
                return;
            }

            if (m.isOwner(e.getClickedBlock(), e.getPlayer())) {

                if (!e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.valueOf(sellItem))) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.valueOf(buyItem), Integer.parseInt(sign.getLine(3).split("/")[1])));
                    sign.setLine(3, sign.getLine(3).split("/")[0] + "/0");
                    sign.update();
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Profits gathered");
                    return;
                }

                if (!e.getPlayer().getInventory().contains(Material.valueOf(sellItem))) {
                    e.getPlayer().sendMessage(ChatColor.RED + "You do not have the item to add to the stock");
                    return;
                }

                int amountToAdd  = e.getPlayer().getInventory().getItemInMainHand().getAmount();
                e.getPlayer().getInventory().getItemInMainHand().setAmount(0);
                int amount = Integer.parseInt(ChatColor.stripColor(sign.getLine(3).split("/")[0]));
                amount = amount + amountToAdd;
                sign.setLine(3, Integer.toString(amount) + "/" + sign.getLine(3).split("/")[1]);
                sign.update();
                e.getPlayer().sendMessage(ChatColor.GREEN + "Stock added! ");
                return;
            }


            if (items.length < 2 || items1.length < 2) {
                e.getPlayer().sendMessage(ChatColor.RED + "Something is wrong on lines 2 and 3: " + string + " " + string2 + " is not valid");
                return;
            }

            //TODO: remove this if statement and test if it still works
            if (!e.getPlayer().getInventory().contains(Material.valueOf(buyItem))) {
                e.getPlayer().sendMessage(ChatColor.RED + "You do not have enough items to sell");
                return;
            }
            //remove up to here

            for (ItemStack item : e.getPlayer().getInventory().getContents()) {
                int amount = Integer.parseInt(ChatColor.stripColor(sign.getLine(3)).split("/")[0]);
                int amount2 = Integer.parseInt(ChatColor.stripColor(sign.getLine(3)).split("/")[1]);
                if (item != null && item.getType().equals(Material.valueOf(buyItem))) {

                    if (item.getAmount() < Integer.parseInt(items1[1])) {
                        e.getPlayer().sendMessage(ChatColor.RED + "You do not have enough items to sell");
                        return;
                    } else {
                        if (amount < Integer.parseInt(items[1])){
                            e.getPlayer().sendMessage(ChatColor.YELLOW + "Out of stock!");
                            return;
                        }
                        item.setAmount(item.getAmount() - Integer.parseInt(items1[1]));
                        amount = amount - Integer.parseInt(items[1]);
                        amount2 = amount2 + Integer.parseInt(items1[1]);
                        sign.setLine(3, Integer.toString(amount) + "/" + Integer.toString(amount2));
                        sign.update();
                        break;
                    }
                }
            }


            e.getPlayer().getInventory().addItem(new ItemStack(Material.valueOf(sellItem), Integer.parseInt(items[1])));
            e.getPlayer().updateInventory();
            e.getPlayer().sendMessage(ChatColor.GREEN + "Trade successful!");
        }catch (Exception youGoofed ){
            e.getPlayer().sendMessage(ChatColor.RED + "Something is incorrectly formatted on this sign");
            System.out.println("[SIMPLESERVER] Error at signs.java: " + youGoofed);
        }

    }
}


