@Override public boolean apply(BlockVector3 position) throws WorldEditException {
  return extent.setBlock(position,pattern.apply(position));
}
