package edu.unca.jderbysh.EventPluginVictim;

import org.bukkit.plugin.java.JavaPlugin;


/*
 * This is the main class of the sample plug-in
 */
public final class EventPluginVictim extends JavaPlugin {
	EventPluginVictimLogger logger;
    /*
     * This is called when your plug-in is enabled
     */
    @Override
    public void onEnable() {
        // save the configuration file
        saveDefaultConfig();
        logger = new EventPluginVictimLogger(this);
        // Create the SampleListener
        new EventPluginVictimListener(this);
        
        // set the command executor for sample
        this.getCommand("sample").setExecutor(new EventPluginVictimCommandExecutor(this));
    }
    public EventPluginVictimLogger getLogging() {
    	return logger;
    }
    /*
     * This is called when your plug-in shuts down
     */
    @Override
    public void onDisable() {
        
    }

}
