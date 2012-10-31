package edu.unca.jderbysh.EventPluginVictim;

import java.text.MessageFormat;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Slime;
/*
 * This is a sample event listener
 */
public class EventPluginVictimListener implements Listener {
    private final EventPluginVictim plugin;
    /*
     * This listener needs to know about the plugin which it came from
     */
    public EventPluginVictimListener(EventPluginVictim plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
        this.plugin = plugin;
    }

    /*
     * Send the sample message to all players that join
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(this.plugin.getConfig().getString("sample.message"));
    }
    
    /*
     * Another example of a event handler. This one will give you the name of
     * the entity you interact with, if it is a Creature it will give you the
     * creature Id.
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        final EntityType entityType = event.getRightClicked().getType();
        
        
        event.getPlayer().sendMessage(MessageFormat.format(
                "You interacted with a {0} it has an id of {1}",
                entityType.getName(),
                entityType.getTypeId()));
    }
    
    @EventHandler
    public void onEggImpact(ProjectileHitEvent e) {
    	if(e.getEntity() instanceof Egg){
    		e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 8);
    	}
    	else if(e.getEntity() instanceof Arrow) {
    		e.getEntity().getShooter().setHealth(e.getEntity().getShooter().getHealth()-2);
    		Player p = (Player)(e.getEntity().getShooter());
    		p.sendMessage("No arrows, man.  Try another projectile.");
    	}
    }
    
    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent e) {
    	if(e.getNewLevel()<5) {
    		e.getPlayer().getLocation().getWorld().spawn(e.getPlayer().getLocation(),Slime.class);
    	}
    }
}
