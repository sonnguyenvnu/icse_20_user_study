public static org.spongepowered.api.world.biome.BiomeType adapt(BiomeType biomeType){
  return Sponge.getRegistry().getType(org.spongepowered.api.world.biome.BiomeType.class,biomeType.getId()).orElse(null);
}
