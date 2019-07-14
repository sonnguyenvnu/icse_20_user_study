@Override public String doQuote(String identifier){
  return "\"" + StringUtils.replaceAll(identifier,"\"","\"\"") + "\"";
}
