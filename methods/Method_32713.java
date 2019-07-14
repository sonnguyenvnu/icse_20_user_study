static int parseYear(String str,int def){
  str=str.toLowerCase(Locale.ENGLISH);
  if (str.equals("minimum") || str.equals("min")) {
    return Integer.MIN_VALUE;
  }
 else   if (str.equals("maximum") || str.equals("max")) {
    return Integer.MAX_VALUE;
  }
 else   if (str.equals("only")) {
    return def;
  }
  return Integer.parseInt(str);
}
