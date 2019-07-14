static public boolean getBooleanOption(String name,Properties options,boolean def){
  boolean value=def;
  if (options.containsKey(name)) {
    String s=options.getProperty(name);
    if (s != null) {
      value="on".equalsIgnoreCase(s) || "1".equals(s) || Boolean.parseBoolean(s);
    }
  }
  return value;
}
