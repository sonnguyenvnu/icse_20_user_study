/** 
 * Create a Bukkit Material form a WorldEdit ItemType
 * @param itemType The WorldEdit ItemType
 * @return The Bukkit Material
 */
public static Material adapt(ItemType itemType){
  checkNotNull(itemType);
  if (!itemType.getId().startsWith("minecraft:")) {
    throw new IllegalArgumentException("Bukkit only supports Minecraft items");
  }
  return Material.getMaterial(itemType.getId().substring(10).toUpperCase(Locale.ROOT));
}
