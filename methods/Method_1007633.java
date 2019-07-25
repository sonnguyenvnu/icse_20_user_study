@Override public void build(EditSession editSession,BlockVector3 position,Pattern pattern,double size) throws MaxChangedBlocksException {
  final double startY=fullHeight ? editSession.getWorld().getMaxY() : position.getBlockY() + size;
  for (double x=position.getBlockX() + size; x > position.getBlockX() - size; --x) {
    for (double z=position.getBlockZ() + size; z > position.getBlockZ() - size; --z) {
      double y=startY;
      final List<BlockState> blockTypes=new ArrayList<>();
      for (; y > position.getBlockY() - size; --y) {
        final BlockVector3 pt=BlockVector3.at(x,y,z);
        final BlockState block=editSession.getBlock(pt);
        if (!block.getBlockType().getMaterial().isAir()) {
          blockTypes.add(block);
          editSession.setBlock(pt,BlockTypes.AIR.getDefaultState());
        }
      }
      BlockVector3 pt=BlockVector3.at(x,y,z);
      Collections.reverse(blockTypes);
      for (int i=0; i < blockTypes.size(); ) {
        if (editSession.getBlock(pt).getBlockType().getMaterial().isAir()) {
          editSession.setBlock(pt,blockTypes.get(i++));
        }
        pt=pt.add(0,1,0);
      }
    }
  }
}
