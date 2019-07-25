@Override public String format(String fieldText){
  return fieldText.replaceAll("\\.\\s+(\\p{Lu})(?=\\.)","\\.$1");
}
