@Override public BaseBlock apply(BlockVector3 position){
  BlockVector3 base=position.add(offset);
  int x=Math.abs(base.getBlockX()) % size.getBlockX();
  int y=Math.abs(base.getBlockY()) % size.getBlockY();
  int z=Math.abs(base.getBlockZ()) % size.getBlockZ();
  return getExtent().getFullBlock(BlockVector3.at(x,y,z).add(origin));
}
