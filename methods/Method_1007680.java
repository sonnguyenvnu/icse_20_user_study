@Override public boolean test(BlockVector3 vector){
  return category.contains(getExtent().getBlock(vector));
}
