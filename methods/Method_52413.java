private boolean isLengthViolation(Node parent){
  return parent.jjtGetChild(2).hasImageEqualTo("length");
}
