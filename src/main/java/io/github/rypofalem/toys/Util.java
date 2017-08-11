package io.github.rypofalem.toys;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class Util {

    public static Location getAvg(List<Location> locations){
        if(locations == null || locations.isEmpty()) return null;
        World world = locations.get(0).getWorld();
        double x,y,z;
        x = y = z = 0;
        for(Location loc : locations){
            if(loc.getWorld() != world) return null;
            x += loc.getX();
            y += loc.getY();
            z += loc.getZ();
        }
        return new Location(world, x/locations.size(), y/locations.size(), z/locations.size());
    }
}
