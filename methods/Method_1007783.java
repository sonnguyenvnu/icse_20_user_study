@Override public void contract(BlockVector3... changes) throws RegionOperationException {
  checkNotNull(changes);
  throw new RegionOperationException("Cannot contract a region intersection");
}
