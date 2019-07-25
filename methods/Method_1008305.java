@Override public Shape build(){
  List<Shape> shapes=new ArrayList<>(this.polygons.size());
  if (wrapdateline) {
    for (    PolygonBuilder polygon : this.polygons) {
      for (      Coordinate[][] part : polygon.coordinates()) {
        shapes.add(jtsGeometry(PolygonBuilder.polygon(FACTORY,part)));
      }
    }
  }
 else {
    for (    PolygonBuilder polygon : this.polygons) {
      shapes.add(jtsGeometry(polygon.toPolygon(FACTORY)));
    }
  }
  if (shapes.size() == 1)   return shapes.get(0);
 else   return new XShapeCollection<>(shapes,SPATIAL_CONTEXT);
}
