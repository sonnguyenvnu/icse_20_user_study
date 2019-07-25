@Override public boolean test(BlockVector3 vector){
  BlockState block=getExtent().getBlock(vector);
  for (  BaseBlock testBlock : blocks) {
    if (testBlock.equalsFuzzy(block)) {
      return true;
    }
  }
  return false;
}
