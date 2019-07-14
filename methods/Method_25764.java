private Object literalValue(Tree arg){
  if (!(arg instanceof LiteralTree)) {
    return null;
  }
  return ((LiteralTree)arg).getValue();
}
