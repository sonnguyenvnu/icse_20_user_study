public static String getDir(String path){
  String subString=path.substring(0,path.lastIndexOf('/'));
  return subString.substring(subString.lastIndexOf('/') + 1,subString.length());
}
