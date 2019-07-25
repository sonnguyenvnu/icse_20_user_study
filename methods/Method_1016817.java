public Instance pipe(Instance instance){
  Alphabet alphabet=this.getAlphabet();
  int underscoreCodePoint=Character.codePointAt("_",0);
  if (instance.getData() instanceof CharSequence) {
    CharSequence characters=(CharSequence)instance.getData();
    int length=-1;
    int numTokens=0;
    int totalCodePoints=Character.codePointCount(characters,0,characters.length());
    for (int i=0; i < totalCodePoints; i++) {
      if (numTokens == tokenBuffer.length - 1) {
        System.err.println("Overflowed token buffer");
        break;
      }
      int codePoint=Character.codePointAt(characters,i);
      int codePointType=Character.getType(codePoint);
      if (codePointType == Character.LOWERCASE_LETTER || codePointType == Character.UPPERCASE_LETTER || codePoint == underscoreCodePoint) {
        length++;
        characterBuffer[length]=codePoint;
      }
 else       if (codePointType == Character.DASH_PUNCTUATION || codePointType == Character.DECIMAL_DIGIT_NUMBER) {
        if (length != -1) {
          length++;
          characterBuffer[length]=codePoint;
        }
      }
 else       if (codePointType == Character.SPACE_SEPARATOR || codePointType == Character.LINE_SEPARATOR || codePointType == Character.PARAGRAPH_SEPARATOR || codePointType == Character.END_PUNCTUATION || codePointType == Character.CONNECTOR_PUNCTUATION || codePointType == Character.START_PUNCTUATION || codePointType == Character.INITIAL_QUOTE_PUNCTUATION || codePointType == Character.FINAL_QUOTE_PUNCTUATION || codePointType == Character.OTHER_PUNCTUATION) {
        if (length != -1) {
          String token=new String(characterBuffer,0,length + 1);
          if (alphabet.contains(token) && length >= minimumLength) {
            tokenBuffer[numTokens]=alphabet.lookupIndex(token);
            numTokens++;
          }
          length=-1;
        }
      }
 else       if (codePointType == Character.COMBINING_SPACING_MARK || codePointType == Character.ENCLOSING_MARK || codePointType == Character.NON_SPACING_MARK || codePointType == Character.TITLECASE_LETTER || codePointType == Character.MODIFIER_LETTER || codePointType == Character.OTHER_LETTER) {
        length++;
        characterBuffer[length]=codePoint;
      }
 else {
      }
    }
    if (length != -1) {
      String token=new String(characterBuffer,0,length + 1);
      if (alphabet.contains(token) && length >= minimumLength) {
        tokenBuffer[numTokens]=alphabet.lookupIndex(token);
        numTokens++;
      }
    }
    int[] tokens=new int[numTokens];
    System.arraycopy(tokenBuffer,0,tokens,0,numTokens);
    instance.setData(new FeatureSequence(alphabet,tokens));
  }
 else {
    throw new IllegalArgumentException("Looking for a CharSequence, found a " + instance.getData().getClass());
  }
  return instance;
}
