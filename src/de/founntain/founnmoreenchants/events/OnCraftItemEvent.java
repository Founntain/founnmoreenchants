package de.founntain.founnmoreenchants.events;

import de.founntain.founnmoreenchants.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class OnCraftItemEvent implements Listener {
    @EventHandler
    public void onCraftItemEvent(CraftItemEvent e){
        this.tryEnchantPlus(e);
    }

    private void tryEnchantPlus(CraftItemEvent e){
        //Check if it is a valid recipe
        if(e.getRecipe() == null) {
            e.setCancelled(true);
            return;
        }

        //Gets the contents of the crafting menu (including the result)
        ItemStack[] contents =  e.getInventory().getContents();

        //Preparing enchantments
        Pair<Enchantment, Integer> ench1 = null, ench2 = null;

        for(ItemStack stack : contents) {
            //Check if the item is a enchanted book otherwise continue
            if(stack.getType() != Material.ENCHANTED_BOOK)
                continue;

            ItemMeta meta = stack.getItemMeta();

            //List for all enchantments on the book
            Map<Enchantment, Integer> enchs;

            //Check if the item has an EnchantmentStorageMeta
            if(meta instanceof EnchantmentStorageMeta)
                enchs = ((EnchantmentStorageMeta) meta).getStoredEnchants();
            else
                continue;

            //If no enchantment was found continue
            if(enchs.size() == 0) continue;

            //Remember enchantments when found
            for(Map.Entry<Enchantment, Integer> entry : enchs.entrySet()) {
                if(ench1 == null) {
                    ench1 = new Pair<>(entry.getKey(), entry.getValue());
                }else {
                    ench2 = new Pair<>(entry.getKey(), entry.getValue());
                }
            }
        }

        //Check if enchantments were found
        if(ench1 == null || ench2 == null)
            e.setCancelled(true);

        //Check if enchantments are the same
        if(ench1.GetItem1() != ench2.GetItem1())
            e.setCancelled(true);

        //Check if enchantments have the same level
        if(ench1.GetItem2() != ench2.GetItem2())
            e.setCancelled(true);

        //Create the result with the new enchantment
        ItemStack result = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();

        //If adding the enchant failed cancel operation
        if(!meta.addStoredEnchant(ench1.GetItem1(), ench1.GetItem2() + 1, true))
            e.setCancelled(true);

        result.setItemMeta(meta);

        //return item to player as crating result
        e.getInventory().setResult(result);
    }
}
