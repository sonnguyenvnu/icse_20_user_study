public static BiomeType adapt(Biome biome){
  return BiomeTypes.get(biome.getRegistryName().toString());
}
