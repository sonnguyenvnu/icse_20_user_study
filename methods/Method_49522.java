private String quote(String identifier){
  return "\"" + identifier.replaceAll("\"","\"\"") + "\"";
}
