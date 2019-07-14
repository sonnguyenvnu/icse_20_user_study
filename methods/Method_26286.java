@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (ASTHelpers.isSameType(state.getSymtab().stringBuilderType,ASTHelpers.getType(tree.getIdentifier()),state) && tree.getArguments().size() == 1) {
    ExpressionTree argument=tree.getArguments().get(0);
    Type type=((JCTree)argument).type;
    if (type.getKind() == TypeKind.CHAR) {
      if (argument.getKind() == Kind.CHAR_LITERAL) {
        char ch=(Character)((LiteralTree)argument).getValue();
        return describeMatch(tree,SuggestedFix.replace(argument,"\"" + Convert.quote(Character.toString(ch)) + "\""));
      }
 else {
        return describeMatch(tree,SuggestedFix.replace(tree,"new StringBuilder().append(" + state.getSourceForNode((JCTree)argument) + ")"));
      }
    }
  }
  return Description.NO_MATCH;
}
