/** 
 * Create a WorldEdit location from a Bukkit location.
 * @param location the Bukkit location
 * @return a WorldEdit location
 */
public static Location adapt(org.bukkit.Location location){
  checkNotNull(location);
  Vector3 position=asVector(location);
  return new com.sk89q.worldedit.util.Location(adapt(location.getWorld()),position,location.getYaw(),location.getPitch());
}
