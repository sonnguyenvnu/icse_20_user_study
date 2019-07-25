@ValueConverter(rule="Icon") public IValueConverter<String> Icon(){
  return new IValueConverter<String>(){
    @Override public String toValue(    String string,    INode node) throws ValueConverterException {
      if (string != null && string.startsWith("\"")) {
        return string.substring(1,string.length() - 1);
      }
      return string;
    }
    @Override public String toString(    String value) throws ValueConverterException {
      if (containsWhiteSpace(value)) {
        return "\"" + value + "\"";
      }
      return value;
    }
  }
;
}
