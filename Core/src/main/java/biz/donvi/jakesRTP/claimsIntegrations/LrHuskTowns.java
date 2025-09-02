package biz.donvi.jakesRTP.claimsIntegrations;

import net.william278.husktowns.api.HuskTownsAPI;
import net.william278.husktowns.api.BukkitHuskTownsAPI;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class LrHuskTowns implements LocationRestrictor {

    protected final Plugin cmPlugin;

    public LrHuskTowns(Plugin cmPlugin) {
        this.cmPlugin = cmPlugin;
    }

    @Override
    public Plugin supporterPlugin() {
        return cmPlugin;
    }

    @Override
    public boolean denyLandingAtLocation(Location location) {
        // Get the API instance, then use Bukkit extensions if available
        final HuskTownsAPI base = HuskTownsAPI.getInstance(); // v3 entrypoint
        if (base instanceof BukkitHuskTownsAPI api) {
            // On Bukkit, adapt org.bukkit.Location -> Position and check if claimed
            return api.isClaimAt(api.getPosition(location));
        }
        // If somehow not on Bukkit API (shouldn't happen on Paper/Spigot), allow landing
        return false;
    }
}
