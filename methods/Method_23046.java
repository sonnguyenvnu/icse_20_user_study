static public final boolean containsNonASCII(String what){
  for (  char c : what.toCharArray()) {
    if (c < 32 || c > 127)     return true;
  }
  return false;
}
