@Override public boolean apply(BlockVector3 position,int depth) throws WorldEditException {
  if (mask.test(position)) {
    if (naturalize(position,depth)) {
      ++affected;
    }
  }
  return true;
}
