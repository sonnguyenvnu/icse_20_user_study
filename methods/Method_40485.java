private boolean isInLoadPath(File dir){
  for (  String s : getLoadPath()) {
    if (new File(s).equals(dir)) {
      return true;
    }
  }
  return false;
}
