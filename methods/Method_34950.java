public static boolean hasPlainTextContent(String contentType){
  String typeStr=(contentType != null) ? contentType.toLowerCase() : "";
  return typeStr.contains("text") && !typeStr.contains("html");
}
