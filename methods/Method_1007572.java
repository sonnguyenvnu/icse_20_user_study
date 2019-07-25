/** 
 * Create a Bukkit location from a WorldEdit position with a Bukkit world.
 * @param world the Bukkit world
 * @param position the WorldEdit position
 * @return a Bukkit location
 */
public static org.bukkit.Location adapt(org.bukkit.World world,BlockVector3 position){
  checkNotNull(world);
  checkNotNull(position);
  return new org.bukkit.Location(world,position.getX(),position.getY(),position.getZ());
}
