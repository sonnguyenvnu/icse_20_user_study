@Override public XShapeCollection<Point> build(){
  List<Point> shapes=new ArrayList<>(coordinates.size());
  for (  Coordinate coord : coordinates) {
    shapes.add(SPATIAL_CONTEXT.makePoint(coord.x,coord.y));
  }
  XShapeCollection<Point> multiPoints=new XShapeCollection<>(shapes,SPATIAL_CONTEXT);
  multiPoints.setPointsOnly(true);
  return multiPoints;
}
