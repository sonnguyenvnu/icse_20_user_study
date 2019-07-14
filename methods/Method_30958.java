@NonNull public static String getDirectory(@NonNull String path){
  int index=indexOfLastSeparator(path);
  return index != -1 ? path.substring(0,index) : ".";
}
