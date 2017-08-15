package io.github.rypofalem.toys.Pets;

import com.winthier.custom.item.CustomItem;
import com.winthier.custom.item.ItemContext;
import com.winthier.custom.item.ItemDescription;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PetCertificate implements CustomItem {
    private PetData data;
    private ItemStack template;

    public PetCertificate(PetData data){
        this.data = data;
        template = new ItemStack(Material.PAPER);
        ItemDescription description = new ItemDescription();
        description.setDisplayName("Pet Certificate (" + data.getName() + ")");
        description.setCategory("Toys");
        description.setDescription("Gives the owner the right to summon a " +  data.getName());
        description.setUsage("Right click to permanently bind this pet to you. Summon it with a Pet Leash.");
        description.apply(template);
    }

    @Override
    public String getCustomId() {
        return "toys:pets." + data.getID();
    }

    @Override
    public ItemStack spawnItemStack(int i) {
        ItemStack item = template.clone();
        item.setAmount(i);
        return item;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event, ItemContext context){
        event.setCancelled(true);
        givePerm(context);
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent event, ItemContext context){
        event.setCancelled(true);
        givePerm(context);
    }

    private void givePerm(ItemContext context){
        Player player = context.getPlayer();
        if(player.hasPermission(this.data.getPerm())){
            player.sendMessage("You already have this pet unlocked! Use a Pet Leash to summon it!");
            return;
        }
        ItemStack item = player.getInventory().getItem(context.getSlot());
        item.setAmount(item.getAmount() - 1);
        player.getInventory().setItem(context.getSlot(), item);
        String command = String.format("perm player %s set %s true", player.getName(), data.getPerm());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1, 1.5f);
        player.sendMessage("You unlocked " + data.getName() + ChatColor.RESET.toString() + "! Use a Pet Leash to summon it!");
    }

}
