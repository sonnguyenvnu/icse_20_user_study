/** 
 * Create a WorldEdit EntityType from a Bukkit one.
 * @param entityType Bukkit EntityType
 * @return WorldEdit EntityType
 */
public static EntityType adapt(org.bukkit.entity.EntityType entityType){
  final String name=entityType.getName();
  if (name == null) {
    return null;
  }
  return EntityTypes.get(name.toLowerCase(Locale.ROOT));
}
