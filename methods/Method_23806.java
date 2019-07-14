protected boolean isEmptyArray(String[] contents){
  for (  String entry : contents) {
    if (entry != null && entry.length() > 0) {
      return false;
    }
  }
  return true;
}
