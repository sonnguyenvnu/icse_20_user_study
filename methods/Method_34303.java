private String extractTypePrefix(String header){
  String existing=header;
  String[] tokens=existing.split(" +");
  if (tokens.length > 1 && !tokens[0].endsWith(",")) {
    existing=StringUtils.arrayToDelimitedString(tokens," ").substring(existing.indexOf(" ") + 1);
  }
  return existing;
}
