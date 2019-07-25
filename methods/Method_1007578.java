/** 
 * Create a WorldEdit BiomeType from a Bukkit one.
 * @param biome Bukkit Biome
 * @return WorldEdit BiomeType
 */
public static BiomeType adapt(Biome biome){
  return BiomeTypes.get(biome.name().toLowerCase(Locale.ROOT));
}
