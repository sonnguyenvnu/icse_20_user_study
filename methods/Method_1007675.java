@Override public boolean apply(BlockVector3 position) throws WorldEditException {
  BlockState block=editSession.getBlock(position);
  if (block.getBlockType() == BlockTypes.GRASS_BLOCK) {
    editSession.setBlock(position.add(0,1,0),temperatePattern.apply(position));
    return true;
  }
 else   if (block.getBlockType() == BlockTypes.SAND) {
    editSession.setBlock(position.add(0,1,0),desertPattern.apply(position));
    return true;
  }
  return false;
}
