package sambkamp.simpleserver.simpleserver;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class methods {

    public boolean isOwner(Block b, Player p){
        List<MetadataValue> meta = b.getMetadata("heh");
        StringBuffer sb = new StringBuffer("");
        String stringFromTheArrow;
        for (MetadataValue value : meta) {
            stringFromTheArrow = value.asString();
            sb.append(stringFromTheArrow);
        }

        return p.getUniqueId().toString().equals(sb.toString());
    }

    public boolean canInteract(Block b, Player p){
        int blockX = b.getLocation().getBlockX();
        int blockZ = b.getLocation().getBlockZ();
        if (blockX >= -98 && blockX <= -72 && blockZ <= 94 && blockZ >= 53) {
            if (!p.getGameMode().equals(GameMode.CREATIVE)) return false;
        }
        return true;
    }

}
