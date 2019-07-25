public Instance pipe(Instance instance){
  int underscoreCodePoint=Character.codePointAt("_",0);
  if (instance.getData() instanceof CharSequence) {
    CharSequence characters=(CharSequence)instance.getData();
    ArrayList<String> tokens=new ArrayList<String>();
    int[] tokenBuffer=new int[1000];
    int length=-1;
    int totalCodePoints=Character.codePointCount(characters,0,characters.length());
    for (int i=0; i < totalCodePoints; i++) {
      int codePoint=Character.codePointAt(characters,i);
      int codePointType=Character.getType(codePoint);
      if (codePointType == Character.LOWERCASE_LETTER || codePointType == Character.UPPERCASE_LETTER || codePoint == underscoreCodePoint) {
        length++;
        tokenBuffer[length]=codePoint;
      }
 else       if (codePointType == Character.SPACE_SEPARATOR || codePointType == Character.LINE_SEPARATOR || codePointType == Character.PARAGRAPH_SEPARATOR || codePointType == Character.END_PUNCTUATION || codePointType == Character.DASH_PUNCTUATION || codePointType == Character.CONNECTOR_PUNCTUATION || codePointType == Character.START_PUNCTUATION || codePointType == Character.INITIAL_QUOTE_PUNCTUATION || codePointType == Character.FINAL_QUOTE_PUNCTUATION || codePointType == Character.OTHER_PUNCTUATION) {
        if (length != -1) {
          String token=new String(tokenBuffer,0,length + 1);
          if (!stoplist.contains(token)) {
            tokens.add(token);
          }
          length=-1;
        }
      }
 else       if (codePointType == Character.COMBINING_SPACING_MARK || codePointType == Character.ENCLOSING_MARK || codePointType == Character.NON_SPACING_MARK || codePointType == Character.TITLECASE_LETTER || codePointType == Character.MODIFIER_LETTER || codePointType == Character.OTHER_LETTER) {
        length++;
        tokenBuffer[length]=codePoint;
      }
 else {
      }
      if (length + 1 == tokenBuffer.length) {
        String token=new String(tokenBuffer,0,length + 1);
        if (!stoplist.contains(token)) {
          tokens.add(token);
        }
        length=-1;
      }
    }
    if (length != -1) {
      String token=new String(tokenBuffer,0,length + 1);
      if (!stoplist.contains(token)) {
        tokens.add(token);
      }
    }
    instance.setData(tokens);
  }
 else {
    throw new IllegalArgumentException("Looking for a CharSequence, found a " + instance.getData().getClass());
  }
  return instance;
}
