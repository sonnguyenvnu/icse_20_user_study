@NonNull public static String removeExtension(@NonNull String path){
  int index=indexOfExtensionSeparator(path);
  return index != -1 ? path.substring(0,index) : path;
}
