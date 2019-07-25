@Override public JtsGeometry build(){
  final Geometry geometry;
  if (wrapdateline) {
    ArrayList<LineString> parts=new ArrayList<>();
    for (    LineStringBuilder line : lines) {
      LineStringBuilder.decompose(FACTORY,line.coordinates(false),parts);
    }
    if (parts.size() == 1) {
      geometry=parts.get(0);
    }
 else {
      LineString[] lineStrings=parts.toArray(new LineString[parts.size()]);
      geometry=FACTORY.createMultiLineString(lineStrings);
    }
  }
 else {
    LineString[] lineStrings=new LineString[lines.size()];
    Iterator<LineStringBuilder> iterator=lines.iterator();
    for (int i=0; iterator.hasNext(); i++) {
      lineStrings[i]=FACTORY.createLineString(iterator.next().coordinates(false));
    }
    geometry=FACTORY.createMultiLineString(lineStrings);
  }
  return jtsGeometry(geometry);
}
