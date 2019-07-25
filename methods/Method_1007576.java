/** 
 * Create a Bukkit Material form a WorldEdit BlockType
 * @param blockType The WorldEdit BlockType
 * @return The Bukkit Material
 */
public static Material adapt(BlockType blockType){
  checkNotNull(blockType);
  if (!blockType.getId().startsWith("minecraft:")) {
    throw new IllegalArgumentException("Bukkit only supports Minecraft blocks");
  }
  return Material.getMaterial(blockType.getId().substring(10).toUpperCase(Locale.ROOT));
}
