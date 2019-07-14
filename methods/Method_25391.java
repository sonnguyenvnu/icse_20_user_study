public static Matcher<ExpressionTree> intLiteral(int value){
  return (tree,state) -> {
    return tree.getKind() == Kind.INT_LITERAL && value == ((Integer)((LiteralTree)tree).getValue());
  }
;
}
