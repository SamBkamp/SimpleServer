package sambkamp.simpleserver.simpleserver;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

//-85, 71, 82
public class spawnEffects implements Listener {

    @EventHandler
    public void playerMovement(PlayerMoveEvent e){
        int playerx = (int)e.getPlayer().getLocation().getX();
        int playery = (int)e.getPlayer().getLocation().getY();
        int playerz = (int)e.getPlayer().getLocation().getZ();
        if(playerx <= -15 && playerx >= -155 && playery >= 1 && playery <= 141){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 2));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1000,1));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000, 2));

        }
    }
}
