package biz.donvi.jakesRTP;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.logging.Level;

import static biz.donvi.jakesRTP.SafeLocationUtils.requireMainThread;

public class SafeLocationFinderBukkitThread extends SafeLocationFinder {

    public SafeLocationFinderBukkitThread(final Location loc) { super(loc); }

    public SafeLocationFinderBukkitThread(
        final Location loc, int checkRadiusXZ, int checkRadiusVert,
        int lowBound, int highBound
    ) { super(loc, checkRadiusXZ, checkRadiusVert, lowBound, highBound); }
    
    @Override
    protected Material getLocMaterial(Location loc) {
        requireMainThread();
        if (!loc.getChunk().isLoaded()) {
            JakesRtpPlugin.log(Level.WARNING,
                "[JRTP] Synchronous chunk load triggered in SafeLocationFinderBukkitThread. " + //This happens when a chunk refuses to be loaded asyncronously. At this point, the chunk must be loaded by any means necessary.
                "This may cause a server stutter. If this warning is frequent, please report it.");
            loc.getChunk().load(); //Sync chunk load; this is a fallback method and should only be needed in extremely rare scenarios (if ever). Otherwise, chunk loading is always async.
        }
        return loc.getBlock().getType();
    }

    @Override
    protected void dropToGround() {
        requireMainThread();
        SafeLocationUtils.util.dropToGround(loc, lowBound, highBound);
    }

    @Override
    protected void dropToMiddle() {
        requireMainThread();
        SafeLocationUtils.util.dropToMiddle(loc, lowBound, highBound);
    }

}
