private void checkArgument(VarSymbol formal,ExpressionTree actual,ErrorProneToken token,VisitorState state){
  List<Comment> matches=new ArrayList<>();
  for (  Comment comment : token.comments()) {
    if (comment.getStyle() != BLOCK) {
      continue;
    }
    Matcher m=NamedParameterComment.PARAMETER_COMMENT_PATTERN.matcher(Comments.getTextFromComment(comment));
    if (!m.matches()) {
      continue;
    }
    String name=m.group(1);
    if (formal.getSimpleName().contentEquals(name)) {
      return;
    }
    matches.add(comment);
  }
  for (  Comment match : matches) {
    state.reportMatch(buildDescription(actual).setMessage(String.format("`%s` does not match formal parameter name `%s`",match.getText(),formal.getSimpleName())).addFix(SuggestedFix.replace(match.getSourcePos(0),match.getSourcePos(match.getText().length() - 1) + 1,String.format("/* %s= */",formal.getSimpleName()))).build());
  }
}
