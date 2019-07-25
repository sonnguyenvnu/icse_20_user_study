@Override public boolean test(BlockVector3 vector){
  Extent extent=getExtent();
  BlockState block=extent.getBlock(vector);
  return block.getBlockType().getMaterial().isMovementBlocker();
}
