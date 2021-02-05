package ru.danka.tntrun;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.lang.reflect.Member;

public class Listener implements org.bukkit.event.Listener {

    private final JavaPlugin javaPlugin;
    boolean aboutX = false;


    public Listener(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    private boolean isAbout(double val) {
        double part = val - (int) val;
        return part > 0.25 && part < 0.75;
    }

    @EventHandler
    public void tntZapor(PlayerMoveEvent e) {
        Location from = e.getFrom();

        Block block = e.getFrom().add(0, -1, 0).getBlock();

        Bukkit.getScheduler().runTaskLater(javaPlugin, () -> {
            if (e.getFrom().getBlock().getType() == Material.SAND || e.getFrom().getBlock().getType() == Material.GRAVEL ){
                setAir(from);

                if(isAbout(e.getFrom().getX())){
                    aboutX = true;
                    setAir(from, new Vector(1, 0, 0));

                }
                if (isAbout(from.getZ())) {
                    setAir(from, new Vector(0, 0, 1));
                    if (aboutX) {
                        setAir(from, new Vector(1, 0, 1));
                    }
                }


            }


            // Миша все хуйня давай по новой
           /* if (block.getType() == Material.SAND || Material.GRAVEL == block.getType()) {
                if (get(e.getFrom().getBlockX()) >= 0.25 || get(e.getFrom().getBlockX()) <= 0.75 &&
                        get(e.getFrom().getBlockZ()) >= 0.25 || get(e.getFrom().getBlockZ()) <= 0.75) {
                    block.setType(Material.AIR);
                    block.getLocation().add(1, 0, 0).getBlock().setType(Material.AIR);
                    block.getLocation().add(-1, 0, 0).getBlock().setType(Material.AIR);
                    block.getLocation().add(0, 0, 1).getBlock().setType(Material.AIR);
                    block.getLocation().add(0, 0, -1).getBlock().setType(Material.AIR);
                    block.getLocation().add(1, -1, 0).getBlock().setType(Material.AIR);
                    block.getLocation().add(-1, -1, 0).getBlock().setType(Material.AIR);
                    block.getLocation().add(0, -1, 1).getBlock().setType(Material.AIR);
                    block.getLocation().add(0, -1, -1).getBlock().setType(Material.AIR);
                    e.getPlayer().sendMessage("Sasi по запористому");
                } else {
                    block.setType(Material.AIR);
                    block.getLocation().add(0, -1, 0).getBlock().setType(Material.AIR);
                    e.getPlayer().sendMessage("Sasi по обычному");
                }

            }*/
        }, 10L);


    }

    public double get(double v) {
        return v - (int) v;
    }

    private void setAir(Location from) {
        from.getBlock().setType(Material.AIR);
        from.add(0, -1, 0).getBlock().setType(Material.AIR);
        from.add(0, 1, 0);
    }

    private void setAir(Location from, Vector vector) {
        from.add(vector);
        setAir(from);
        from.add(vector.multiply(-1));
    }

}
