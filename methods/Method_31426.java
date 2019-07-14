static String pgQuote(String identifier){
  return "\"" + StringUtils.replaceAll(identifier,"\"","\"\"") + "\"";
}
