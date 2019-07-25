static ArrayList<LineString> decompose(GeometryFactory factory,Coordinate[] coordinates,ArrayList<LineString> strings){
  for (  Coordinate[] part : decompose(+DATELINE,coordinates)) {
    for (    Coordinate[] line : decompose(-DATELINE,part)) {
      strings.add(factory.createLineString(line));
    }
  }
  return strings;
}
