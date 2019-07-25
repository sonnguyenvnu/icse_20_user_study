/** 
 * Create a Bukkit location from a WorldEdit location.
 * @param location the WorldEdit location
 * @return a Bukkit location
 */
public static org.bukkit.Location adapt(Location location){
  checkNotNull(location);
  Vector3 position=location.toVector();
  return new org.bukkit.Location(adapt((World)location.getExtent()),position.getX(),position.getY(),position.getZ(),location.getYaw(),location.getPitch());
}
