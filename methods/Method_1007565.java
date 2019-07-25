/** 
 * Create a WorldEdit world from a Bukkit world.
 * @param world the Bukkit world
 * @return a WorldEdit world
 */
public static World adapt(org.bukkit.World world){
  checkNotNull(world);
  return new BukkitWorld(world);
}
