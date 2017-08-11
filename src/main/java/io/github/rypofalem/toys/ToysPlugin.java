package io.github.rypofalem.toys;

import com.winthier.custom.event.CustomRegisterEvent;
import io.github.rypofalem.toys.Pets.PetItem;
import io.github.rypofalem.toys.Pets.PetManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ToysPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCustomRegister(CustomRegisterEvent event){
        Bukkit.getPluginManager().registerEvents(new PetManager(), this);
        event.addItem(new PetItem());
        Bukkit.getScheduler().runTaskTimer(this, new BukkitRunnable() {
            private int tick = 0;
            @Override
            public void run() {
                PetManager.tickPets(tick);
                tick++;
            }
        }, 1, 1);
    }
}
