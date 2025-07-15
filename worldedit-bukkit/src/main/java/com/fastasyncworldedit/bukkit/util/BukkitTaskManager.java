package com.fastasyncworldedit.bukkit.util;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.fastasyncworldedit.core.util.TaskManager;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;

public class BukkitTaskManager extends TaskManager {

    private final WorldEditPlugin plugin;

    public BukkitTaskManager(final WorldEditPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public int repeat(@Nonnull final Runnable runnable, final int interval) {
        return WorldEditPlugin.getScheduler().runTaskTimer(this.plugin, runnable, interval, interval);
    }

    @Override
    public int repeatAsync(@Nonnull final Runnable runnable, final int interval) {
        return WorldEditPlugin.getScheduler().runTaskTimerAsynchronously(this.plugin, runnable, interval, interval);
    }

    @Override
    public void async(@Nonnull final Runnable runnable) {
        WorldEditPlugin.getScheduler().runTaskAsynchronously(this.plugin, runnable).getTaskId();
    }

    @Override
    public void task(@Nonnull final Runnable runnable) {
        WorldEditPlugin.getScheduler().runTask(this.plugin, runnable).getTaskId();
    }

    @Override
    public void later(@Nonnull final Runnable runnable, final int delay) {
        WorldEditPlugin.getScheduler().runTaskLater(this.plugin, runnable, delay).getTaskId();
    }

    @Override
    public void laterAsync(@Nonnull final Runnable runnable, final int delay) {
        WorldEditPlugin.getScheduler().runTaskLaterAsynchronously(this.plugin, runnable, delay);
    }

    @Override
    public void cancel(final int task) {
        try {
            Bukkit.getScheduler().cancelTask(task);
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Error cancelling FAWE task in Folia", e);
        }
    }

}
