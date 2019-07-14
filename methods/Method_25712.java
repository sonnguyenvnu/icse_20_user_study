/** 
 * Determine the kind of match we have between the comments on this argument and the formal parameter name.
 */
static MatchedComment match(Commented<ExpressionTree> actual,String formal){
  Optional<Comment> lastBlockComment=Streams.findLast(actual.beforeComments().stream().filter(c -> c.getStyle() == CommentStyle.BLOCK));
  if (lastBlockComment.isPresent()) {
    Matcher m=PARAMETER_COMMENT_PATTERN.matcher(Comments.getTextFromComment(lastBlockComment.get()));
    if (m.matches()) {
      return MatchedComment.create(lastBlockComment.get(),m.group(1).equals(formal) ? MatchType.EXACT_MATCH : MatchType.BAD_MATCH);
    }
  }
  Optional<Comment> approximateMatchComment=Stream.concat(actual.beforeComments().stream(),actual.afterComments().stream()).filter(comment -> isApproximateMatchingComment(comment,formal)).findFirst();
  if (approximateMatchComment.isPresent()) {
    String text=CharMatcher.anyOf("=:").trimTrailingFrom(Comments.getTextFromComment(approximateMatchComment.get()).trim());
    return MatchedComment.create(approximateMatchComment.get(),text.equals(formal) ? MatchType.EXACT_MATCH : MatchType.APPROXIMATE_MATCH);
  }
  return MatchedComment.notAnnotated();
}
