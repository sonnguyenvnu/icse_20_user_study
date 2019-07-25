@Override public boolean apply(BlockVector3 position) throws WorldEditException {
  if (!editSession.getBlock(position).getBlockType().getMaterial().isAir()) {
    position=position.add(0,1,0);
  }
  if (editSession.getBlock(position.add(0,-1,0)).getBlockType() != BlockTypes.GRASS_BLOCK) {
    return false;
  }
  BlockState leavesBlock=BlockTypes.OAK_LEAVES.getDefaultState();
  if (editSession.getBlock(position).getBlockType().getMaterial().isAir()) {
    editSession.setBlock(position,leavesBlock);
  }
  placeVine(position,position.add(0,0,1));
  placeVine(position,position.add(0,0,-1));
  placeVine(position,position.add(1,0,0));
  placeVine(position,position.add(-1,0,0));
  return true;
}
