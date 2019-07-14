static public int getIntegerOption(String name,Properties options,int def){
  int value=def;
  if (options.containsKey(name)) {
    String s=options.getProperty(name);
    if (s != null) {
      try {
        value=Integer.parseInt(s);
      }
 catch (      NumberFormatException e) {
      }
    }
  }
  return value;
}
