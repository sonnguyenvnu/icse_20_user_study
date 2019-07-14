static private void adjustLegacyIntegerOption(String format,Properties parameters,ObjectNode optionObj,String legacyName,String newName){
  String s=parameters.getProperty(legacyName);
  if (s != null && !s.isEmpty()) {
    try {
      JSONUtilities.safePut(optionObj,newName,Integer.parseInt(s));
    }
 catch (    NumberFormatException e) {
    }
  }
}
