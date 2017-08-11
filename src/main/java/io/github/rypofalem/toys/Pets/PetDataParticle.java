package io.github.rypofalem.toys.Pets;

import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public class PetDataParticle extends PetData {
    private Particle particle;

    public PetDataParticle(String name, ItemStack icon, String description, Particle particle) {
        super(name, icon, description);
        this.particle = particle;
    }

    @Override
    public Pet create(Player player){
        return new PetParticle(this, player);
    }
}
