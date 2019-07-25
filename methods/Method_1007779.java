@Override public List<BlockVector2> polygonize(int maxPoints){
  if (maxPoints >= 0 && maxPoints < 4) {
    throw new IllegalArgumentException("Cannot polygonize an AbstractRegion with no overridden polygonize method into less than 4 points.");
  }
  final BlockVector3 min=getMinimumPoint();
  final BlockVector3 max=getMaximumPoint();
  final List<BlockVector2> points=new ArrayList<>(4);
  points.add(BlockVector2.at(min.getX(),min.getZ()));
  points.add(BlockVector2.at(min.getX(),max.getZ()));
  points.add(BlockVector2.at(max.getX(),max.getZ()));
  points.add(BlockVector2.at(max.getX(),min.getZ()));
  return points;
}
