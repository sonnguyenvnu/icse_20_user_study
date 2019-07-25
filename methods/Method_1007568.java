/** 
 * Create a Bukkit world from a WorldEdit world.
 * @param world the WorldEdit world
 * @return a Bukkit world
 */
public static org.bukkit.World adapt(World world){
  checkNotNull(world);
  if (world instanceof BukkitWorld) {
    return ((BukkitWorld)world).getWorld();
  }
 else {
    org.bukkit.World match=Bukkit.getServer().getWorld(world.getName());
    if (match != null) {
      return match;
    }
 else {
      throw new IllegalArgumentException("Can't find a Bukkit world for " + world.getName());
    }
  }
}
