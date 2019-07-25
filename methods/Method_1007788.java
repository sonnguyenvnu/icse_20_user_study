/** 
 * Generates the shape.
 * @param editSession The EditSession to use.
 * @param baseBiome The default biome type.
 * @param hollow Specifies whether to generate a hollow shape.
 * @return number of affected blocks.
 */
public int generate(EditSession editSession,BiomeType baseBiome,boolean hollow){
  int affected=0;
  for (  BlockVector2 position : getExtent()) {
    int x=position.getBlockX();
    int z=position.getBlockZ();
    if (!hollow) {
      final BiomeType material=getBiome(x,z,baseBiome);
      if (material != null && material != BiomeTypes.THE_VOID) {
        editSession.getWorld().setBiome(position,material);
        ++affected;
      }
      continue;
    }
    final BiomeType material=getBiomeCached(x,z,baseBiome);
    if (material == null) {
      continue;
    }
    boolean draw=false;
    do {
      if (!isInsideCached(x + 1,z,baseBiome)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x - 1,z,baseBiome)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x,z + 1,baseBiome)) {
        draw=true;
        break;
      }
      if (!isInsideCached(x,z - 1,baseBiome)) {
        draw=true;
        break;
      }
    }
 while (false);
    if (!draw) {
      continue;
    }
    editSession.getWorld().setBiome(position,material);
    ++affected;
  }
  return affected;
}
