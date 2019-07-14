public static String getPhotoNameByPath(String path){
  String b[]=path.split("/");
  String fi=b[b.length - 1];
  return fi.substring(0,fi.lastIndexOf('.'));
}
