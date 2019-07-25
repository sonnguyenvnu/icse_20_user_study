private boolean naturalize(BlockVector3 position,int depth) throws WorldEditException {
  BlockState block=editSession.getBlock(position);
  BlockState targetBlock=getTargetBlock(depth);
  if (block.equalsFuzzy(targetBlock)) {
    return false;
  }
  return editSession.setBlock(position,targetBlock);
}
