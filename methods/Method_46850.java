private String getNameForPath(String path){
  if (path.isEmpty())   return "";
  final StringBuilder stringBuilder=new StringBuilder(path);
  stringBuilder.deleteCharAt(path.length() - 1);
  try {
    return stringBuilder.substring(stringBuilder.lastIndexOf("/") + 1);
  }
 catch (  StringIndexOutOfBoundsException e) {
    return path.substring(0,path.lastIndexOf("/"));
  }
}
