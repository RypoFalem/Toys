package io.github.rypofalem.toys.Pets;


import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter
public abstract class Pet {
    public abstract String getPerm();
    public abstract ItemStack getIcon();
    protected abstract void onTick(int tick);
    protected abstract UUID getPlayer();
}