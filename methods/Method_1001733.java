public boolean eval(){
  nextChar();
  final boolean x=parseExpression();
  if (pos < str.length()) {
    throw new IllegalArgumentException("Unexpected: " + (char)ch);
  }
  return x;
}
