private static boolean exclude(String fileName){
  for (  String e : EXCLUDE) {
    if (fileName.endsWith(e)) {
      return true;
    }
  }
  return false;
}
