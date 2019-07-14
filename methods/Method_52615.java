private void parseClassName(String[] names){
  if (names != null) {
    for (    String s : names) {
      parseClassName(s);
    }
  }
}
