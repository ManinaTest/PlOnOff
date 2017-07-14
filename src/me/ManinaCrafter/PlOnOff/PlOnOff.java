package me.ManinaCrafter.PlOnOff;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlOnOff extends org.bukkit.plugin.java.JavaPlugin {

	final String prefix = ChatColor.RED + "[" + ChatColor.DARK_RED + "PlOnOff" + ChatColor.RED + "]";
	  
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String cmdLabel, String[] args) {
	    if ((sender instanceof Player)) {
	    	Player player = (Player)sender;
	    	if (cmdLabel.equalsIgnoreCase("plonoff")) {
	    		Plugin[] plugins;
	    		String s = "";
	    	    int cnt = (plugins = getServer().getPluginManager().getPlugins()).length;
	    	    for (int i = 0; i < cnt; i++) {
	    	    	Plugin p = plugins[i];
	    	    	if (getServer().getPluginManager().isPluginEnabled(p)) {
	    	    		if (!s.equals("")) s += ", ";
	    	    		s += p.getName() + " (enabled)";
	    	    	}
	    	    	else {
	    	    		if (!s.equals("")) s += ", ";
	    	    		s += p.getName() + " (disabled)";
	    	    	}
	    	    }
	    	    s = "Plugins (" + cnt + "): " + s;
    			sender.sendMessage(this.prefix + ChatColor.WHITE + " " + s);
	    	}
	    	else if (cmdLabel.equalsIgnoreCase("ploff")) {
	    		if ((args.length == 0) && (player.hasPermission("plonoff.disable.all"))) {
	    			int cnt = disableAll();
	    			sender.sendMessage(this.prefix + ChatColor.WHITE + " " + cnt + " plugins " + ChatColor.RED + "disabled.");
	    		}
	    		else if ((args.length > 0) && (player.hasPermission("plonoff.disable"))) {
	    			String nam = disablePlugin(args[0]);
	    			if (nam == null) sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " was not found!");
	    			else if (nam != "") sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + nam + ChatColor.RED + " was disabled.");
	    			else sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " is already disabled.");
	    		}
	    		else sender.sendMessage(ChatColor.DARK_RED + " You Don't Have Permission To Do That!");
	    	}
	    	else if (cmdLabel.equalsIgnoreCase("plon")) {
	    		if ((args.length == 0) && (player.hasPermission("plonoff.enable.all"))) {
	    			int cnt = enableAll();
	    			sender.sendMessage(this.prefix + ChatColor.WHITE + " " + cnt + " plugins " + ChatColor.GREEN + "enabled.");
	    		}
	    		else if ((args.length > 0) && (player.hasPermission("plonoff.enable"))) {
	    			String nam = enablePlugin(args[0]);
	    			if (nam == null) sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " was not found!");
	    			else if (nam != "") sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + nam + ChatColor.RED + " was enabled.");
	    			else sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " is already enabled.");
	    		}
	    		else sender.sendMessage(ChatColor.DARK_RED + "You Don't Have Permission To Do That!");
	    	}
	    }
	    else if (cmdLabel.equalsIgnoreCase("plonoff")) {
    		Plugin[] plugins;
    		String s = "";
    	    int cnt = (plugins = getServer().getPluginManager().getPlugins()).length;
    	    for (int i = 0; i < cnt; i++) {
    	    	Plugin p = plugins[i];
    	    	if (getServer().getPluginManager().isPluginEnabled(p)) {
    	    		if (!s.equals("")) s += ", ";
    	    		s += p.getName() + " (enabled)";
    	    	}
    	    	else {
    	    		if (!s.equals("")) s += ", ";
    	    		s += p.getName() + " (disabled)";
    	    	}
    	    }
    	    s = "Plugins (" + cnt + "): " + s;
    		getLogger().info(s);
	    }
	    else if (cmdLabel.equalsIgnoreCase("ploff")) {
	    	if (args.length == 0) {
	    		int cnt = disableAll();
	    		getLogger().info(cnt + " plugins disabled.");
	    	}	    		
	    	else {
	    		String nam = disablePlugin(args[0]);
	    		if (nam == null) getLogger().info("The Plugin " + args[0] + " was not found!");
	    		else if (nam != "") getLogger().info("The Plugin " + nam + " was disabled.");
	    		else getLogger().info("The Plugin " + args[0] + " is already disabled.");
	    	}
	    }
	    else if (cmdLabel.equalsIgnoreCase("plon")) {
	    	if (args.length == 0) {
	    		int cnt = enableAll();
	    		getLogger().info(cnt + " plugins enabled.");
	    	}
	    	else {
	    		String nam = enablePlugin(args[0]);
	    		if (nam == null) getLogger().info("The Plugin " + args[0] + " was not found!");
	    		else if (nam != "") getLogger().info("The Plugin " + nam + " was enabled.");
	    		else getLogger().info("The Plugin " + args[0] + " is already enabled.");
	    	}
	    }
	    return true;
	}

	private int enableAll() {
		int cntO = 0;
		Plugin[] plugins;
	    int cnt = (plugins = getServer().getPluginManager().getPlugins()).length;
	    for (int i = 0; i < cnt; i++) {
	    	Plugin p = plugins[i];
	    	if (!getServer().getPluginManager().isPluginEnabled(p)) {
	    		getServer().getPluginManager().enablePlugin(p);
	    		cntO++;
	    	}
	    }
	    return cntO;
	}

	private String enablePlugin(String pluginName) {
		String nam = null;
		Plugin[] plugins;
	    int cnt = (plugins = getServer().getPluginManager().getPlugins()).length;
	    for (int i = 0; i < cnt; i++) {
	    	Plugin p = plugins[i];
	    	if (p.getName().equalsIgnoreCase(pluginName)) {
		    	if (!getServer().getPluginManager().isPluginEnabled(p)) {
		    		getServer().getPluginManager().enablePlugin(p);
		    		nam = p.getName(); 
		    	}
		    	else nam = "";
	    		break;
	    	}
	    }
	    return nam;
	}

	private int disableAll() {
		int cntO = 0;
		Plugin[] plugins;
		int cnt = (plugins = getServer().getPluginManager().getPlugins()).length;
		for (int i = 0; i < cnt; i++) {
			Plugin p = plugins[i];
	    	if (getServer().getPluginManager().isPluginEnabled(p) && (!p.getName().equalsIgnoreCase("plonoff"))) {
				getServer().getPluginManager().disablePlugin(p);
				cntO++;
			}
		}
	    return cntO;
	}

	private String disablePlugin(String pluginName) {
		String nam = null;
		Plugin[] plugins;
	    int cnt = (plugins = getServer().getPluginManager().getPlugins()).length;
	    for (int i = 0; i < cnt; i++) {
	    	Plugin p = plugins[i];
	    	if (!(p.getName().equalsIgnoreCase("plonoff")) && (p.getName().equalsIgnoreCase(pluginName))) {
		    	if (getServer().getPluginManager().isPluginEnabled(p)) {
		    		getServer().getPluginManager().disablePlugin(p);
		    		nam = p.getName(); 
		    	}
		    	else nam = "";
	    		break;
	    	}
	    }
	    return nam;
	}

}
