private static String lineStringCoordinatesFromWkt(String wkt){
  wkt=removeBrackets(wkt,1);
  return getJsonArrayFromListOfPoints(wkt);
}
