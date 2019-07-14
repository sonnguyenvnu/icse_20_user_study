@Override public void condComment(final CharSequence expression,final boolean isStartingTag,final boolean isHidden,final boolean isHiddenEndTag){
  String expressionString=expression.toString().trim();
  if (expressionString.equals("endif")) {
    enabled=true;
    return;
  }
  if (expressionString.equals("if !IE")) {
    enabled=false;
    return;
  }
  float ieVersion=domBuilder.config.getCondCommentIEVersion();
  if (htmlCCommentExpressionMatcher == null) {
    htmlCCommentExpressionMatcher=new HtmlCCommentExpressionMatcher();
  }
  enabled=htmlCCommentExpressionMatcher.match(ieVersion,expressionString);
}
