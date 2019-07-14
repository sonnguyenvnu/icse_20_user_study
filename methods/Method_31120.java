private static boolean isFlagSet(String[] args,String flag){
  for (  String arg : args) {
    if (flag.equals(arg)) {
      return true;
    }
  }
  return false;
}
