private static String multiLineStringCoordinatesFromWkt(String wkt){
  wkt=removeBrackets(wkt,1);
  String lineStringsWithPipeSeparator=wkt.replaceAll("\\s*\\)\\s*,\\s*\\(",")|(");
  String[] lineStrings=lineStringsWithPipeSeparator.split("\\|");
  String[] coordinates=new String[lineStrings.length];
  for (int i=0; i < lineStrings.length; i++) {
    coordinates[i]=lineStringCoordinatesFromWkt(lineStrings[i]);
  }
  String multiLineStringCoordinates=Joiner.on(",").join(coordinates);
  return String.format("[%s]",multiLineStringCoordinates);
}
