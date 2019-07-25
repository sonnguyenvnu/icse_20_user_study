@ValueConverter(rule="Command") public IValueConverter<String> Command(){
  return new AbstractNullSafeConverter<String>(){
    @Override protected String internalToValue(    String string,    INode node){
      if ((string.startsWith("'") && string.endsWith("'")) || (string.startsWith("\"") && string.endsWith("\""))) {
        return STRING().toValue(string,node);
      }
      return ID().toValue(string,node);
    }
    @Override protected String internalToString(    String value){
      if (ID_PATTERN.matcher(value).matches()) {
        return ID().toString(value);
      }
 else {
        return STRING().toString(value);
      }
    }
  }
;
}
