/** 
 * Create a Bukkit location from a WorldEdit location with a Bukkit world.
 * @param world the Bukkit world
 * @param location the WorldEdit location
 * @return a Bukkit location
 */
public static org.bukkit.Location adapt(org.bukkit.World world,Location location){
  checkNotNull(world);
  checkNotNull(location);
  return new org.bukkit.Location(world,location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
}
