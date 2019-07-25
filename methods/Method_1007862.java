public static Biome adapt(BiomeType biomeType){
  return ForgeRegistries.BIOMES.getValue(new ResourceLocation(biomeType.getId()));
}
