static public Properties parseParameters(Properties p,String str){
  if (str != null) {
    String[] pairs=str.split("&");
    for (    String pairString : pairs) {
      int equal=pairString.indexOf('=');
      String name=(equal >= 0) ? pairString.substring(0,equal) : "";
      String value=(equal >= 0) ? ParsingUtilities.decode(pairString.substring(equal + 1)) : "";
      p.put(name,value);
    }
  }
  return p;
}
