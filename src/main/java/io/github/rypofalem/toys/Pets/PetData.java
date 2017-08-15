package io.github.rypofalem.toys.Pets;

import com.winthier.custom.item.ItemDescription;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class PetData {
    @Getter private String ID;
    @Getter private String name;
    @Getter private ItemStack icon;
    @Getter private String description;

    PetData(String ID, String name, ItemStack icon, String description){
        ItemDescription meta = new ItemDescription();
        meta.setDisplayName(name);
        meta.setDescription(description);
        meta.apply(icon);
        this.icon = icon;
        this.description = description;
        this.name = name;
        this.ID = ID.toLowerCase();
    }

    String getPerm(){
        return "toys.pets." + ID;
    }

    public abstract Pet create(Player player);
}