private Fix appendCharset(Tree tree,Tree select,List<? extends ExpressionTree> arguments,VisitorState state,CharsetFix charset){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  if (arguments.isEmpty()) {
    fix.replace(state.getEndPosition(select),state.getEndPosition(tree),String.format("(%s)",charset.replacement()));
  }
 else {
    fix.postfixWith(Iterables.getLast(arguments),", " + charset.replacement());
  }
  charset.addImport(fix,state);
  return fix.build();
}
