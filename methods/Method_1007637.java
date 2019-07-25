@Override public void build(EditSession editSession,BlockVector3 position,Pattern pattern,double size) throws MaxChangedBlocksException {
  Vector3 posDouble=position.toVector3();
  Location min=new Location(editSession.getWorld(),posDouble.subtract(size,size,size));
  BlockVector3 max=posDouble.add(size,size + 10,size).toBlockPoint();
  Region region=new CuboidRegion(editSession.getWorld(),min.toVector().toBlockPoint(),max);
  HeightMap heightMap=new HeightMap(editSession,region,mask);
  HeightMapFilter filter=new HeightMapFilter(new GaussianKernel(5,1.0));
  heightMap.applyFilter(filter,iterations);
}
