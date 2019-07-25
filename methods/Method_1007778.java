@Override public void shift(BlockVector3 change) throws RegionOperationException {
  expand(change);
  contract(change);
}
