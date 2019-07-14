static String redshiftQuote(String identifier){
  return "\"" + StringUtils.replaceAll(identifier,"\"","\"\"") + "\"";
}
