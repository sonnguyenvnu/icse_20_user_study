protected static boolean containsVersionDirective(String[] shSrc){
  for (int i=0; i < shSrc.length; i++) {
    String line=shSrc[i];
    int versionIndex=line.indexOf("#version");
    if (versionIndex >= 0) {
      int commentIndex=line.indexOf("//");
      if (commentIndex < 0 || versionIndex < commentIndex) {
        return true;
      }
    }
  }
  return false;
}
