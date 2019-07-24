package sambkamp.simpleserver.simpleserver;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("helpme")){
            if(args.length < 1) {
                sender.sendMessage(ChatColor.AQUA + "Welcome to Bkamp's smp server \n /helpme 1 for general rules \n /helpme 2 for help with trading posts \n /helpme 3 for help with Buy and Sell signs \n /credits for credits and source code");
                return true;
            } else if (args[0].equals("1")) {
                sender.sendMessage(ChatColor.YELLOW + "This smp is similar in style to rust. So pvp is allowed in most areas (except for spawn and trading posts) \n and you can team up with other players. Trading is allowed however trading posts are encouraged");
                return true;
            } else if (args[0].equals("2")) {
                sender.sendMessage(ChatColor.YELLOW + "Trading posts are structures you can generate by placing a 'shop builder' fence. you can put up signs to sells stuff. PvP is disabled inside the posts and the post can not be damaged by other players. You can see what the structures look like by visiting the example if you head west from spawn, you spawn with one shop builder when you spawn for the first time. after that you can purchase mroe for 10 iron at spawn");
                return true;
            } else if (args[0].equals("3")) {
                sender.sendMessage(ChatColor.YELLOW + "To set up a sign shop, you place a sign and in the first row write [TRADE] - [your name], on the second row you put the item id you want to sell (you can find thing out with /sellids) and next to it the amount per trade. On the third row you would do the same except for the item you want to recieve in the trade. On the last row you just put 0/0, this represents the stock of the item youre selling and the amount of items youve sold. You can add to this by clicking on your own sign with the item in your inventory. an Example would look like this: \n [TRADE] - Bkamp \n IRON_INGOT, 5 \n DIAMOND, 1 \n 0");
                return true;
            } else {
                sender.sendMessage("please attach valid arguments to your command");
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("credits")){
            sender.sendMessage(ChatColor.YELLOW + "This plugin was developed by SamBkamp, you can see the sourcecode at: \n github.com/SamBkamp/SimpleServer");
        }else if (command.getName().equalsIgnoreCase("sellids")) {
            sender.sendMessage(ChatColor.YELLOW + "sell ids: \n Iron ingot = IRON_INGOT \n Gold Ingot = GOLD_INGOT \n Diamonds = DIAMOND \n Coal = COAL \n Coal Block = COAL_BLOCK");
        }else if(command.getName().equalsIgnoreCase("spawn")){
            Player player = (Player) sender;
            Location location = new Location(player.getWorld(), 140, 68, -130);
            player.teleport(location);
        }
        return true;
    }
}