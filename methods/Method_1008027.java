@Override public void recover(final Parser recognizer,final RecognitionException re){
  final Token token=re.getOffendingToken();
  String message;
  if (token == null) {
    message="no parse token found.";
  }
 else   if (re instanceof InputMismatchException) {
    message="unexpected token [" + getTokenErrorDisplay(token) + "]" + " was expecting one of [" + re.getExpectedTokens().toString(recognizer.getVocabulary()) + "].";
  }
 else   if (re instanceof NoViableAltException) {
    if (token.getType() == PainlessParser.EOF) {
      message="unexpected end of script.";
    }
 else {
      message="invalid sequence of tokens near [" + getTokenErrorDisplay(token) + "].";
    }
  }
 else {
    message="unexpected token near [" + getTokenErrorDisplay(token) + "].";
  }
  Location location=new Location(sourceName,token == null ? -1 : token.getStartIndex());
  throw location.createError(new IllegalArgumentException(message,re));
}
