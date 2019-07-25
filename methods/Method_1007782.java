@Override public void expand(BlockVector3... changes) throws RegionOperationException {
  checkNotNull(changes);
  throw new RegionOperationException("Cannot expand a region intersection");
}
