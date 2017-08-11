package io.github.rypofalem.toys.Pets;

import com.winthier.custom.inventory.CustomInventory;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu implements CustomInventory{
    static final int size = 27;
    @Getter Inventory inventory;
    
    public Menu(Player player){
        inventory = Bukkit.createInventory(player, size, "Choose your pet");
        inventory.setContents(createIcons(player));
    }

    private static ItemStack[] createIcons(Player player) {
        ItemStack[] icons = new ItemStack[size];
        int count = 0;
        for(PetData pet : PetManager.getPets()){
            if(count >= size) break;
            if(player.hasPermission(pet.getPerm())){
                icons[count] = pet.getIcon();
                count++;
            }
        }
        return icons;
    }

    public void onInventoryOpen(InventoryOpenEvent event) {
    }

    public void onInventoryClose(InventoryCloseEvent event) {
    }

    public void onInventoryInteract(InventoryInteractEvent event) {
        event.setCancelled(true);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null ) return;
        if(event.getClickedInventory().getType() != InventoryType.CHEST) return;
        event.setCancelled(true);

        if(event.getSlot() == 0){
            PetManager.stowPet(event.getWhoClicked().getUniqueId());
            return;
        }
        ItemStack icon = event.getInventory().getContents()[event.getSlot()];
        if(icon == null || icon.getType() == Material.AIR) return;
        for(PetData pet : PetManager.getPets()){
            if(pet.getIcon().equals(icon)) PetManager.setPet(event.getWhoClicked().getUniqueId(), pet.create(Bukkit.getPlayer(event.getWhoClicked().getUniqueId())));
        }
    }

    public void onInventoryDrag(InventoryDragEvent event) {
        event.setCancelled(true);
    }
}
