public static String getAlbumPathRenamed(String olderPath,String newName){
  return olderPath.substring(0,olderPath.lastIndexOf('/')) + "/" + newName;
}
