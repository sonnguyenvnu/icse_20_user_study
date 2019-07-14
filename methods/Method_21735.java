private static String multiPolygonCoordinatesFromWkt(String wkt){
  wkt=removeBrackets(wkt,1);
  String polygonsWithPipeSeparator=wkt.replaceAll("\\s*\\)\\s*\\)\\s*,\\s*\\(\\s*\\(\\s*","))|((");
  String[] polygons=polygonsWithPipeSeparator.split("\\|");
  String[] polygonsCoordinates=new String[polygons.length];
  for (int i=0; i < polygons.length; i++) {
    polygonsCoordinates[i]=polygonCoordinatesFromWkt(polygons[i]);
  }
  String coordinates=Joiner.on(",").join(polygonsCoordinates);
  return String.format("[%s]",coordinates);
}
