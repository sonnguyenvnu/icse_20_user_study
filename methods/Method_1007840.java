public static Biome adapt(BiomeType biomeType){
  return Registry.BIOME.get(new Identifier(biomeType.getId()));
}
