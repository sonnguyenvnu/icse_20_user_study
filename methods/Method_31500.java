@Override protected boolean isDelimiter(String peek,Delimiter delimiter){
  return peek.length() >= 2 && (peek.charAt(0) == 'G' || peek.charAt(0) == 'g') && (peek.charAt(1) == 'O' || peek.charAt(1) == 'o') && (peek.length() == 2 || Character.isWhitespace(peek.charAt(2)));
}
