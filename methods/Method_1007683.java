@Override public boolean test(BlockVector3 vector){
  return blocks.contains(getExtent().getBlock(vector).getBlockType());
}
