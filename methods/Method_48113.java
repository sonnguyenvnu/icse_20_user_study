public Geoshape polygon(List<double[]> coordinates){
  Preconditions.checkArgument(coordinates.size() >= 4,"Too few coordinate pairs provided");
  Preconditions.checkArgument(Arrays.equals(coordinates.get(0),coordinates.get(coordinates.size() - 1)),"Polygon is not closed");
  final PolygonBuilder builder=this.getContext().getShapeFactory().polygon();
  for (  double[] coordinate : coordinates) {
    Preconditions.checkArgument(coordinate.length == 2 && Geoshape.isValidCoordinate(coordinate[1],coordinate[0]),"Invalid coordinate provided");
    builder.pointXY(coordinate[0],coordinate[1]);
  }
  return new Geoshape(builder.build());
}
