private boolean contains(String string,String... totest){
  for (  String t : totest) {
    if (string.contains(t)) {
      return true;
    }
  }
  return false;
}
