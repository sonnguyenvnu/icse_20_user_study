@Override public JtsGeometry build(){
  Coordinate[] coordinates=this.coordinates.toArray(new Coordinate[this.coordinates.size()]);
  Geometry geometry;
  if (wrapdateline) {
    ArrayList<LineString> strings=decompose(FACTORY,coordinates,new ArrayList<LineString>());
    if (strings.size() == 1) {
      geometry=strings.get(0);
    }
 else {
      LineString[] linestrings=strings.toArray(new LineString[strings.size()]);
      geometry=FACTORY.createMultiLineString(linestrings);
    }
  }
 else {
    geometry=FACTORY.createLineString(coordinates);
  }
  return jtsGeometry(geometry);
}
