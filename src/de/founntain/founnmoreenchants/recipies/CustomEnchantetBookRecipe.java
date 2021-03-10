package de.founntain.founnmoreenchants.recipies;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class CustomEnchantetBookRecipe {
    private Plugin plugin;

    public CustomEnchantetBookRecipe(Plugin plugin) {
        this.plugin = plugin;

        this.createRecipe();

        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "registered " + this.getClass().getSimpleName());
    }

    public void createRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "customEnchantetBookRecipe"), new ItemStack(Material.ENCHANTED_BOOK));

        recipe.addIngredient(Material.ENCHANTED_BOOK);
        recipe.addIngredient(Material.ENCHANTED_BOOK);

        plugin.getServer().addRecipe(recipe);
    }
}
