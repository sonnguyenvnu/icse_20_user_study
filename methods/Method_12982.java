protected static boolean checkHelpFlag(String[] args){
  if (args != null) {
    for (    String arg : args) {
      if ("-h".equals(arg)) {
        return true;
      }
    }
  }
  return false;
}
