public double eval(){
  nextChar();
  double x=parseExpression();
  if (pos < str.length()) {
    throw new RuntimeException("Unexpected: " + (char)ch);
  }
  return x;
}
