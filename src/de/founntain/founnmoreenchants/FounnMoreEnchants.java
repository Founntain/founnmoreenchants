package de.founntain.founnmoreenchants;

import de.founntain.founnmoreenchants.commands.InfoCommand;
import de.founntain.founnmoreenchants.events.OnCraftItemEvent;
import de.founntain.founnmoreenchants.recipies.CustomEnchantetBookRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FounnMoreEnchants extends JavaPlugin {
    private final Server server;
    private final String consolePrefix =  ChatColor.WHITE + "[" + ChatColor.BLUE +"FounnMoreEnchants" + ChatColor.WHITE + "] ";

    public FounnMoreEnchants(){
        this.server = this.getServer();
    }

    @Override
    public void onEnable(){
        //Registering Events
        this.sendConsoleMessage(ChatColor.YELLOW + "registering events");

        this.registerEvents();

        this.sendConsoleMessage(ChatColor.GREEN + "finished registering events");

        //Registering Commands
        this.sendConsoleMessage(ChatColor.YELLOW + "registering commands");

        this.registerCommands();

        this.sendConsoleMessage(ChatColor.GREEN +  "finished registering commands");
    }

    @Override
    public void onLoad(){
        //Registering Recipes
        this.sendConsoleMessage(ChatColor.YELLOW + "registering recipes");

        this.registerRecipies();

        this.sendConsoleMessage(ChatColor.GREEN +  "finished registering recipes");
    }

    private void sendConsoleMessage(String msg) {
        this.server.getConsoleSender().sendMessage(this.consolePrefix + msg);
    }

    private void registerEvent(Listener listener) {
        this.server.getPluginManager().registerEvents(listener, this);
    }

    private void registerCommand(String command, CommandExecutor commandExecutor) {
        this.getCommand(command).setExecutor(commandExecutor);
    }

    private void registerEvents(){
        this.registerEvent(new OnCraftItemEvent());
    }

    private void registerCommands(){
        this.registerCommand("fmeinfo", new InfoCommand());
    }

    private void registerRecipies(){
        new CustomEnchantetBookRecipe(this);
    }
}
