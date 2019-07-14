private static String getJsonArrayFromListOfPoints(String pointsInWkt){
  String[] points=pointsInWkt.split(",");
  List<String> coordinates=new ArrayList<>();
  for (  String point : points) {
    coordinates.add(extractCoordinateFromPoint(point));
  }
  String joinedCoordinates=Joiner.on(",").join(coordinates);
  return String.format("[%s]",joinedCoordinates);
}
