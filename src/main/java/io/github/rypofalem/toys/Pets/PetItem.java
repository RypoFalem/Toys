package io.github.rypofalem.toys.Pets;

import com.winthier.custom.CustomPlugin;
import com.winthier.custom.item.CustomItem;
import com.winthier.custom.item.ItemDescription;
import com.winthier.custom.item.UncraftableItem;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Getter
public class PetItem implements CustomItem, UncraftableItem {
    final String customId = "toys:petitem";
    ItemStack template;

    public PetItem(){
        template = new ItemStack(Material.LEASH);
        ItemDescription description = new ItemDescription();
        description.setDisplayName("Pet Leash");
        description.setUsage("Right click to open your pet menu.");
        description.setDescription("This item stores your cosmetic pets!");
        description.setCategory("Toys");
        description.apply(template);
    }

    @Override
    public ItemStack spawnItemStack(int i) {
        return template.clone();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            openMenu(event.getPlayer());
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityInteract(PlayerInteractEntityEvent event){
        openMenu(event.getPlayer());
        event.setCancelled(true);
    }

    public void openMenu(Player player){
        CustomPlugin.getInstance().getInventoryManager().openInventory(player, new Menu(player));
    }
}
