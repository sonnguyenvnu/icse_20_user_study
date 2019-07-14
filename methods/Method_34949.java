public static boolean hasBinaryContent(String contentType){
  String typeStr=(contentType != null) ? contentType.toLowerCase() : "";
  return typeStr.contains("image") || typeStr.contains("audio") || typeStr.contains("video") || typeStr.contains("application");
}
