@Override public void reset(){
  CharSequence input=inputCharSequence();
  clearAttributes();
  tokens=Lists.newArrayList();
  tokenTypes=Lists.newArrayList();
  tokenIndex=0;
  if (input.length() == 0) {
    return;
  }
 else   if (input.length() == 1) {
    char c=input.charAt(0);
    if (isSpace(c)) {
      return;
    }
 else     if (isLetter(c)) {
      tokens.add(CharBuffer.wrap(input));
      tokenTypes.add(TokenType.TOKEN);
      return;
    }
  }
  Matcher matcher=delimiterPattern.matcher(input);
  int lastMatch=0;
  while (matcher.find()) {
    if (matcher.start() != lastMatch) {
      tokens.add(CharBuffer.wrap(input,lastMatch,matcher.start()));
      tokenTypes.add(TokenType.TOKEN);
    }
    if (keepPunctuation && matcher.start(punctuationGroup) >= 0) {
      tokens.add(CharBuffer.wrap(input,matcher.start(punctuationGroup),matcher.end(punctuationGroup)));
      tokenTypes.add(TokenType.PUNCTUATION);
    }
    lastMatch=matcher.end();
  }
  if (lastMatch < input.length()) {
    tokens.add(CharBuffer.wrap(input,lastMatch,input.length()));
    tokenTypes.add(TokenType.TOKEN);
  }
}
