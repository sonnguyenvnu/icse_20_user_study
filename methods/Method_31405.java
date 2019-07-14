@Override protected boolean isAlternativeStringLiteral(String peek){
  if (peek.length() < 3) {
    return false;
  }
  return peek.charAt(0) == 'q' && peek.charAt(1) == '\'';
}
