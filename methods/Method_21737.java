private static String polygonCoordinatesFromWkt(String wkt){
  wkt=removeBrackets(wkt,2);
  String coordinates;
  boolean polygonContainsInnerHoles=wkt.contains("(");
  if (polygonContainsInnerHoles) {
    String[] polygons=wkt.split("\\s*\\)\\s*,\\s*\\(\\s*");
    String[] coordinatesOfPolygons=new String[polygons.length];
    for (int i=0; i < polygons.length; i++) {
      String polygonCoordinates=getJsonArrayFromListOfPoints(polygons[i]);
      coordinatesOfPolygons[i]=polygonCoordinates;
    }
    coordinates=Joiner.on(",").join(coordinatesOfPolygons);
  }
 else {
    coordinates=getJsonArrayFromListOfPoints(wkt);
  }
  return String.format("[%s]",coordinates);
}
