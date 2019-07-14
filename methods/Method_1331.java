public static boolean hasMimeType(String mimeType){
  return sMimeTypeToExtensionMap.containsKey(mimeType) || sMimeTypeMap.hasMimeType(mimeType);
}
