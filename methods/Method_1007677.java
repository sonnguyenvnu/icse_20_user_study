@Override public boolean apply(BlockVector3 position) throws WorldEditException {
  return world.useItem(position,item,dir);
}
