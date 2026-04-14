package biz.donvi.jakesRTP.claimsIntegrations;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public interface LocationRestrictor {
    
    Plugin supporterPlugin();
    
    boolean denyLandingAtLocation(Location location);
}
