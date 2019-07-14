private static int getThrowsPosition(MethodTree tree,VisitorState state){
  for (  ErrorProneToken token : state.getOffsetTokensForNode(tree)) {
    if (token.kind() == Tokens.TokenKind.THROWS) {
      return token.pos();
    }
  }
  throw new AssertionError();
}
