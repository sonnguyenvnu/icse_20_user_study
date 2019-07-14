private static String getDirsFromUrl(String urlPath){
  int pos=urlPath.lastIndexOf("/") + 1;
  String root=urlPath.substring(0,pos);
  return root;
}
