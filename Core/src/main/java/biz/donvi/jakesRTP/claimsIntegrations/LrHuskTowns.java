package biz.donvi.jakesRTP.claimsIntegrations;

import net.william278.husktowns.api.HuskTownsAPI;
import net.william278.husktowns.api.BukkitHuskTownsAPI;
import net.william278.husktowns.claim.Position;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class LrHuskTowns implements LocationRestrictor {

    protected Plugin cmPlugin;

    public LrHuskTowns(Plugin cmPlugin) {
        this.cmPlugin = cmPlugin;
    }

    @Override
    public Plugin supporterPlugin() {
        return cmPlugin;
    }

    @Override
    public boolean denyLandingAtLocation(Location location) {
        // Convert Bukkit Location to HuskTowns Position and check if it's claimed (not wilderness)
        Position position = BukkitHuskTownsAPI.getInstance().getPosition(location);
        return position != null && HuskTownsAPI.getInstance().isClaimAt(position);
    }
}
