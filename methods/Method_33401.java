private static String extractSvgPath(String svgString){
  return svgString.replaceFirst(".*d=\"","").replaceFirst("\".*","");
}
