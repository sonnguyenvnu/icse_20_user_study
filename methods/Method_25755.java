private static boolean hasParameterComment(ErrorProneToken token){
  return token.comments().stream().filter(c -> c.getStyle() == BLOCK).anyMatch(c -> NamedParameterComment.PARAMETER_COMMENT_PATTERN.matcher(Comments.getTextFromComment(c)).matches());
}
