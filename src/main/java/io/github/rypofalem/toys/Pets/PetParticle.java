package io.github.rypofalem.toys.Pets;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;

public class PetParticle extends Pet{
    @Getter private PetDataParticle data;
    @Getter private UUID player;

    private static Vector offset = new Vector(0,0,0);
    private static int currentTick = -1;
    private static float yaw=0;
    private static float pitch=0;

    public PetParticle(PetDataParticle data, Player player){
        this.data = data;
        this.player = player == null ? null : player.getUniqueId();
    }

    @Override
    public String getPerm() {
        return data.getPerm();
    }

    @Override
    public ItemStack getIcon() {
        return data.getIcon();
    }

    @Override
    protected void onTick(int tick) {
        Location loc = Bukkit.getPlayer(getPlayer()).getEyeLocation().clone().add(0,1,0);
        if(tick != currentTick){
            currentTick = tick;
            offset = new Vector(0,0,0);
            loc.setYaw(yaw);
            loc.setPitch(0);
            offset.add(loc.getDirection());
            loc.setYaw(0);
            loc.setPitch(pitch);
            offset.add(loc.getDirection());
            pitch = (pitch+1.5f) % 360;
            yaw = (yaw+1)  % 360;
        }
        loc.add(offset);
        loc.getWorld().spawnParticle(data.getParticle(), loc, 3, .02,.02,.02,0);
    }
}
