private static String convertToJsType(Object value,String scriptLang,Mapping mapping) throws PermanentBackendException {
  final String esValue;
  try {
    esValue=mapWriter.writeValueAsString(convertToEsType(value,mapping));
  }
 catch (  final IOException e) {
    throw new PermanentBackendException("Could not write json");
  }
  return scriptLang.equals("groovy") ? esValue.replace("$","\\$") : esValue;
}
