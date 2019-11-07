package sambkamp.simpleserver.simpleserver;

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

}
