@Override public void recover(final LexerNoViableAltException lnvae){
  final CharStream charStream=lnvae.getInputStream();
  final int startIndex=lnvae.getStartIndex();
  final String text=charStream.getText(Interval.of(startIndex,charStream.index()));
  Location location=new Location(sourceName,_tokenStartCharIndex);
  String message="unexpected character [" + getErrorDisplay(text) + "].";
  char firstChar=text.charAt(0);
  if ((firstChar == '\'' || firstChar == '"') && text.length() - 2 > 0 && text.charAt(text.length() - 2) == '\\') {
    message+=" The only valid escape sequences in strings starting with [" + firstChar + "] are [\\\\] and [\\" + firstChar + "].";
  }
  throw location.createError(new IllegalArgumentException(message,lnvae));
}
