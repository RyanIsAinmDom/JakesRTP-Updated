package biz.donvi.jakesRTP.claimsIntegrations;

import me.angeschossen.lands.api.LandsIntegration;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class LrLands implements LocationRestrictor {
    private final Plugin lands;
    private final LandsIntegration api;

    public LrLands(Plugin landsPlugin, Plugin rtpPlugin) {
        this.lands = landsPlugin;
        this.api = LandsIntegration.of(rtpPlugin);
    }

    @Override
    public Plugin supporterPlugin() {
        return lands;
    }

    @Override
    public boolean denyLandingAtLocation(Location location) {
        // isClaimed(...) is deprecated/removed on the new API; check for an Area instead
        return api.getUnloadedArea(location) != null;
    }
}
