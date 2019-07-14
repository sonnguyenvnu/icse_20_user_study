private static String escapeValue(Object value){
  return ClientUtils.escapeQueryChars(value.toString());
}
