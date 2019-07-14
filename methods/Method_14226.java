static private void adjustLegacyBooleanOption(String format,Properties parameters,ObjectNode optionObj,String legacyName,String newName,boolean invert){
  String s=parameters.getProperty(legacyName);
  if (s != null && !s.isEmpty()) {
    JSONUtilities.safePut(optionObj,newName,Boolean.parseBoolean(s));
  }
}
