/** 
 * Checks equality between a WorldEdit BlockType and a Bukkit Material
 * @param blockType The WorldEdit BlockType
 * @param type The Bukkit Material
 * @return If they are equal
 */
public static boolean equals(BlockType blockType,Material type){
  return Objects.equals(blockType.getId(),type.getKey().toString());
}
