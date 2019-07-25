public static String root(String qualifiedName){
  int loc=qualifiedName.indexOf(".");
  return (loc < 0) ? qualifiedName : qualifiedName.substring(0,loc);
}
