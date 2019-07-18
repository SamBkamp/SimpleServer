package sambkamp.simpleserver.simpleserver;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class signs implements Listener {
    @EventHandler
    public void sign(PlayerInteractEvent e) {

        try {
            e.getClickedBlock().getType();
        }catch (Exception E){
            return;
        }

        if(!e.getClickedBlock().getType().equals(Material.OAK_SIGN) || !e.getClickedBlock().getType().equals(Material.OAK_WALL_SIGN)) {
            return;
        }


        Sign sign = (Sign) e.getClickedBlock().getState();
        String amp[] = ChatColor.stripColor(sign.getLine(0)).split(" - ");

        if (!amp[0].equalsIgnoreCase("[T]")) {
            return;
        }
        String string  = ChatColor.stripColor(sign.getLine(1));
        String[] items = string.split(", ");
        String string2  = ChatColor.stripColor(sign.getLine(2));
        String[] items1 = string2.split(", ");


        if (e.getPlayer().getName().equals(amp[1])){

            if (!e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.valueOf(items[0]))){
                e.getPlayer().getInventory().addItem(new ItemStack(Material.valueOf(items[0]), Integer.parseInt(sign.getLine(3).split("/")[1])));
                sign.setLine(3, sign.getLine(3).split("/")[0] + "/0");
                sign.update();
                e.getPlayer().sendMessage(ChatColor.GREEN + "Profits gathered");
                return;
            }

            if(!e.getPlayer().getInventory().contains(Material.valueOf(items[0]))){
                e.getPlayer().sendMessage(ChatColor.RED + "You do not have the item to add to the stock");
                return;
            }

            for (ItemStack item : e.getPlayer().getInventory().getContents()) {
                if (item != null && item.getType().equals(Material.valueOf(items[0]))){
                    item.setAmount(item.getAmount() - 1);
                    break;
                }
            }
            int amount = Integer.parseInt(ChatColor.stripColor(sign.getLine(3).split("/")[0]));
            amount++;
            sign.setLine(3, Integer.toString(amount) + "/" + sign.getLine(3).split("/")[1]);
            sign.update();
            e.getPlayer().sendMessage(ChatColor.GREEN + "Stock added! ");
            return;
        }


        if(items.length < 2 || items1.length < 2){
            e.getPlayer().sendMessage(ChatColor.RED + "Something is wrong on lines 2 and 3: " + string + " " + string2 + " is not valid");
            return;
        }

        //TODO: remove this if statement and test if it still works
        if (!e.getPlayer().getInventory().contains(Material.valueOf(items1[0]))){
            e.getPlayer().sendMessage(ChatColor.RED + "You do not have enough items to sell");
            return;
        }
        //remove up to here

        for (ItemStack item : e.getPlayer().getInventory().getContents()) {
            if (item != null && item.getType().equals(Material.valueOf(items1[0]))){

                if (item.getAmount() < Integer.parseInt(items1[1])){
                    e.getPlayer().sendMessage(ChatColor.RED + "You do not have enough items to sell");
                    return;
                }else {
                    item.setAmount(item.getAmount() - Integer.parseInt(items1[1]));
                    int amount = Integer.parseInt(ChatColor.stripColor(sign.getLine(3)).split("/")[0]);
                    amount--;
                    int amount2 = Integer.parseInt(ChatColor.stripColor(sign.getLine(3)).split("/")[1]);
                    amount2++;
                    sign.setLine(3, Integer.toString(amount) + "/" + Integer.toString(amount2));
                    sign.update();
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Trade successful!");
                    break;
                }
            }
        }

        Player p = Bukkit.getPlayer(amp[1]);

        e.getPlayer().getInventory().addItem(new ItemStack(Material.valueOf(items[0]), Integer.parseInt(items[1])));
        p.getInventory().addItem(new ItemStack(Material.valueOf(items1[0]), Integer.parseInt(items1[1])));
        e.getPlayer().updateInventory();
        e.getPlayer().sendMessage(ChatColor.GREEN + "trade successful!");
    }
}


