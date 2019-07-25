/** 
 * Create a WorldEdit entity from a Bukkit entity.
 * @param entity the Bukkit entity
 * @return a WorldEdit entity
 */
public static Entity adapt(org.bukkit.entity.Entity entity){
  checkNotNull(entity);
  return new BukkitEntity(entity);
}
