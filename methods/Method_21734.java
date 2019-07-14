private static String multiPointCoordinatesFromWkt(String wkt){
  wkt=removeBrackets(wkt,1);
  boolean isSecondVersionMultiPoint=wkt.contains("(");
  String coordinates="";
  if (isSecondVersionMultiPoint) {
    wkt=wkt.replaceAll("\\(|\\)","");
  }
  coordinates=getJsonArrayFromListOfPoints(wkt);
  return coordinates;
}
