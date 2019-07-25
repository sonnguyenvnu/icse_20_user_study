private static String substring(String in){
  if (in.length() > 1024) {
    return in.substring(0,1024) + "... (" + (in.length() - 1024) + " chars omitted)";
  }
  return in;
}
