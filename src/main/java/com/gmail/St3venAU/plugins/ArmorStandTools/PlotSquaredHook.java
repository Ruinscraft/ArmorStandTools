package com.gmail.St3venAU.plugins.ArmorStandTools;

import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

@SuppressWarnings("deprecation")
class PlotSquaredHook {

	private static Main plugin;

	public PlotSquaredHook(Main main) {
		plugin = main;
	}

	public static boolean isPlotWorld(Location loc) {
		World world = loc.getWorld();
		return PS.get().hasPlotArea(world.getName());
	}

	public static boolean checkPermission(Player player, Location loc) {
		PlotPlayer plotPlayer = PlotPlayer.wrap(player);
		Plot plot = plotPlayer.getCurrentPlot();
		plugin.debug("Plot: " + plot);
		if (plot == null) {
			plugin.debug("plots.admin.build.road: " + plotPlayer.hasPermission("plots.admin.build.road"));
			return plotPlayer.hasPermission("plots.admin.build.road");
		}
		UUID uuid = plotPlayer.getUUID();
		plugin.debug("plot.isAdded: " + plot.isAdded(uuid));
		plugin.debug("plots.admin.build.other: " + plotPlayer.hasPermission("plots.admin.build.other"));
		return plot.isAdded(uuid) || plotPlayer.hasPermission("plots.admin.build.other");
	}
}
