@Override public boolean test(BlockVector3 vector){
  return !getExtent().getBlock(vector).getBlockType().getMaterial().isAir();
}
