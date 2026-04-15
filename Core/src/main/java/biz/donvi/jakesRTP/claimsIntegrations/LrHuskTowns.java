package biz.donvi.jakesRTP.claimsIntegrations;

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
        BukkitHuskTownsAPI api = BukkitHuskTownsAPI.getBukkitInstance();
        Position position = api.getPosition(location);
        return api.isClaimAt(position).isPresent();
    }
