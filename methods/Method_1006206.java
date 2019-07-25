@Override public String format(String fieldText){
  if (fieldText == null) {
    return null;
  }
  String result=fieldText;
  if (result.startsWith("/")) {
    result=result.substring(1);
  }
  return DOI.parse(result).map(DOI::getURIAsASCIIString).orElse(result);
}
