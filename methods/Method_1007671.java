@Override public boolean apply(BlockVector2 position) throws WorldEditException {
  return extent.setBiome(position,biome);
}
