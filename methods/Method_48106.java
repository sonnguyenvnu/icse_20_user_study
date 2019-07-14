/** 
 * Constructs a line from list of coordinates
 * @param coordinates Coordinate (lon,lat) pairs
 * @return
 */
public static Geoshape line(List<double[]> coordinates){
  Preconditions.checkArgument(coordinates.size() >= 2,"Too few coordinate pairs provided");
  final LineStringBuilder builder=getShapeFactory().lineString();
  for (  double[] coordinate : coordinates) {
    Preconditions.checkArgument(isValidCoordinate(coordinate[1],coordinate[0]),"Invalid coordinate provided");
    builder.pointXY(coordinate[0],coordinate[1]);
  }
  return new Geoshape(builder.build());
}
