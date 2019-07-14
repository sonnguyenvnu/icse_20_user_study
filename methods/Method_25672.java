@Nullable private static Comment findOrphanedJavadoc(Name name,List<ErrorProneToken> tokens){
  for (  ErrorProneToken token : tokens) {
    for (    Comment comment : token.comments()) {
      if (comment.getText().startsWith("/**")) {
        return comment;
      }
    }
    if (token.kind() == TokenKind.IDENTIFIER && token.name().equals(name)) {
      return null;
    }
  }
  return null;
}
