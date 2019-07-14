private void appendCharsets(Description.Builder description,Tree tree,Tree select,List<? extends ExpressionTree> arguments,VisitorState state){
  description.addFix(appendCharset(tree,select,arguments,state,CharsetFix.UTF_8_FIX));
  description.addFix(appendCharset(tree,select,arguments,state,CharsetFix.DEFAULT_CHARSET_FIX));
}
