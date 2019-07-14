public static boolean hasCssTextContent(String contentType){
  String typeStr=(contentType != null) ? contentType.toLowerCase() : "";
  return typeStr.contains("css");
}
