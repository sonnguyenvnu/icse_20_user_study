public void writeFieldValue(char seperator,String name,String value){
  if (quoteFieldNames) {
    if (useSingleQuotes) {
      write(seperator);
      writeFieldName(name);
      if (value == null) {
        writeNull();
      }
 else {
        writeString(value);
      }
    }
 else {
      if (isEnabled(SerializerFeature.BrowserCompatible)) {
        write(seperator);
        writeStringWithDoubleQuote(name,':');
        writeStringWithDoubleQuote(value,(char)0);
      }
 else {
        writeFieldValueStringWithDoubleQuoteCheck(seperator,name,value);
      }
    }
  }
 else {
    write(seperator);
    writeFieldName(name);
    if (value == null) {
      writeNull();
    }
 else {
      writeString(value);
    }
  }
}
