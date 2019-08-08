package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class signs implements Listener {
    @EventHandler
    public void sign(PlayerInteractEvent e) {

        try {
            e.getClickedBlock().getType();
        } catch ( Exception E ) {
            return;
        }

        if (e.getClickedBlock().getType().equals(Material.OAK_SIGN) || e.getClickedBlock().getType().equals(Material.OAK_WALL_SIGN)) {
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

            if(items[0].contains("_CONC")) {
                if(!items[0].contains("_CONCRETE")){
                    sellItem = items[0] + "RETE";
                }
            }

            if(items1[0].contains("_CONC")) {
                if(!items1[0].contains("_CONCRETE")){
                    buyItem = items1[0] + "RETE";
                }
            }

            if (!e.getClickedBlock().hasMetadata("heh")){
                return;
            }
//            List<MetadataValue> meta = e.getClickedBlock().getMetadata("heh");
//            StringBuffer sb = new StringBuffer("");
//            String stringFromTheArrow;
//            for (MetadataValue value : meta) {
//                stringFromTheArrow = value.asString();
//                sb.append(stringFromTheArrow);
//            } here just incase it breaks lol
            spawnBreak sb = new spawnBreak();
            if (sb.isOwner(e.getClickedBlock(), e.getPlayer())) {

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

                for (ItemStack item : e.getPlayer().getInventory().getContents()) {
                    if (item != null && item.getType().equals(Material.valueOf(sellItem))) {
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


