public static BiomeType adapt(Biome biome){
  return BiomeTypes.get(Registry.BIOME.getId(biome).toString());
}
