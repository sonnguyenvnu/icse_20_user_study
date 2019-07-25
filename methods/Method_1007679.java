@Override public boolean test(BlockVector2 vector){
  BiomeType biome=extent.getBiome(vector);
  return biomes.contains(biome);
}
