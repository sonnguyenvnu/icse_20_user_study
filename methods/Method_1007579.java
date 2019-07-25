public static Biome adapt(BiomeType biomeType){
  if (!biomeType.getId().startsWith("minecraft:")) {
    throw new IllegalArgumentException("Bukkit only supports vanilla biomes");
  }
  try {
    return Biome.valueOf(biomeType.getId().substring(10).toUpperCase(Locale.ROOT));
  }
 catch (  IllegalArgumentException e) {
    return null;
  }
}
