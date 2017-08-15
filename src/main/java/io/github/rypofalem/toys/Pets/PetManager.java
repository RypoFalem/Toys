package io.github.rypofalem.toys.Pets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PetManager implements Listener{
    private static List<PetData> petTypes = null;
    private static Map<UUID,Pet> playerPets = new HashMap<>();

    public static List<PetData> getPets(){
        if(petTypes == null){
            petTypes = new ArrayList<>();
            petTypes.add(new PetDataParticle(ChatColor.DARK_RED.toString() + "Crimson Fairy",
                    new ItemStack(Material.REDSTONE), "A blood red fairy.", Particle.REDSTONE));
            petTypes.add(new PetDataParticle(ChatColor.DARK_GRAY.toString() + "Ghastly Fairy",
                    new ItemStack(Material.COAL), "Spoopy.", Particle.SPELL_MOB_AMBIENT));
            petTypes.add(new PetDataParticle(ChatColor.DARK_AQUA.toString() + "Water Fairy",
                    new ItemStack(Material.WATER_BUCKET), "Are bubbles water or air?", Particle.WATER_BUBBLE));
            petTypes.add(new PetDataParticle(ChatColor.WHITE.toString() + "Bright Fairy",
                    new ItemStack(Material.END_ROD), "A very smart fairy.", Particle.END_ROD));
            petTypes.add(new PetDataParticle(ChatColor.RED.toString() + "Flame Fairy",
                    new ItemStack(Material.FLINT_AND_STEEL), "Better double check fire-spread in your claim...", Particle.FLAME));
            petTypes.add(new PetDataParticle(ChatColor.GREEN.toString() + "Slime Fairy",
                    new ItemStack(Material.SLIME_BALL), "Is THAT what that smell is!?", Particle.TOTEM));
            petTypes.add(new PetDataParticle(ChatColor.GRAY.toString() + "Saliva Fairy",
                    new ItemStack(Material.CAULDRON_ITEM), "The spittin' image of spat spit.", Particle.SPIT));
            petTypes.add(new PetDataParticle(ChatColor.DARK_PURPLE.toString() + "Magic Fairy",
                    new ItemStack(Material.CHORUS_FRUIT_POPPED), "Extraordinarily generic!", Particle.PORTAL));
        }
        return Collections.unmodifiableList(petTypes);
    }

    public static Pet getActivePet(UUID uuid){
        return playerPets.get(uuid);
    }

    public static void setPet(UUID uuid, Pet pet){
        playerPets.put(uuid, pet);
    }

    public static void stowPet(UUID uuid){
        playerPets.remove(uuid);
    }

    public static void tickPets(int tick){
        for(UUID uuid : playerPets.keySet()){
            playerPets.get(uuid).onTick(tick);
        }
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event){
        stowPet(event.getPlayer().getUniqueId());
    }
}
