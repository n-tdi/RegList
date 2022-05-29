package ntdi.world.reglisteners;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class RegListeners extends JavaPlugin {

    private static RegListeners regListeners;

    @Override
    public void onLoad() {
        regListeners = this;
    }


    /**
     * Registers all listeners inside your plugin.
     *
     * @param plugin Your plugin
     */
    public void registerListeners(Plugin plugin) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<? extends Listener> listener : getExtendingClasses(plugin, Listener.class)) {
            plugin.getServer().getPluginManager().registerEvents(listener.getConstructor().newInstance(), plugin);
        }
    }

    /**
     * Gets all non-abstract, non-interface classes which extend a certain class within a plugin
     *
     * @param plugin The plugin
     * @param clazz  The class
     * @param <T>    The type of the class
     * @return The list of matching classes
     */
    private static <T> List<Class<? extends T>> getExtendingClasses(Plugin plugin, Class<T> clazz) {
        List<Class<? extends T>> list = new ArrayList<>();
        try {
            ClassLoader loader = plugin.getClass().getClassLoader();
            JarFile file = new JarFile(new File(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()));
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                String name = entry.getName();
                if (!name.endsWith(".class")) {
                    continue;
                }
                name = name.substring(0, name.length() - 6).replace("/", ".");
                Class<?> c;
                try {
                    c = Class.forName(name, true, loader);
                } catch (ClassNotFoundException | NoClassDefFoundError ex) {
                    continue;
                }
                if (!clazz.isAssignableFrom(c) || Modifier.isAbstract(c.getModifiers()) || c.isInterface()) {
                    continue;
                }
                list.add((Class<? extends T>) c);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return list;
    }
}
