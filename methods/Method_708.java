public void writeFieldName(String key,boolean checkSpecial){
  if (key == null) {
    write("null:");
    return;
  }
  if (useSingleQuotes) {
    if (quoteFieldNames) {
      writeStringWithSingleQuote(key);
      write(':');
    }
 else {
      writeKeyWithSingleQuoteIfHasSpecial(key);
    }
  }
 else {
    if (quoteFieldNames) {
      writeStringWithDoubleQuote(key,':');
    }
 else {
      boolean hashSpecial=key.length() == 0;
      for (int i=0; i < key.length(); ++i) {
        char ch=key.charAt(i);
        boolean special=(ch < 64 && (sepcialBits & (1L << ch)) != 0) || ch == '\\';
        if (special) {
          hashSpecial=true;
          break;
        }
      }
      if (hashSpecial) {
        writeStringWithDoubleQuote(key,':');
      }
 else {
        write(key);
        write(':');
      }
    }
  }
}
