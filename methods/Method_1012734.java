private static String quote(String s){
  if (quoted(s)) {
    return s;
  }
  return "\"" + s + "\"";
}
