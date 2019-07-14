@Override public Description matchTypeCast(TypeCastTree typeCastTree,VisitorState state){
  ExpressionTree newInstanceTree=ASTHelpers.stripParentheses(typeCastTree.getExpression());
  if (!CTOR_NEW_INSTANCE.matches(newInstanceTree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree treeReceiver=ASTHelpers.getReceiver(newInstanceTree);
  if (!CLASS_GET_DECLARED_CTOR.matches(treeReceiver,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree classForName=ASTHelpers.getReceiver(treeReceiver);
  if (!CLASS_FOR_NAME.matches(classForName,state)) {
    return Description.NO_MATCH;
  }
  Symbol typeSym=ASTHelpers.getSymbol(typeCastTree.getType());
  if (typeSym == null) {
    return Description.NO_MATCH;
  }
  Types types=state.getTypes();
  Type typeCastTreeType=ASTHelpers.getType(typeCastTree.getType());
  Type erasedType=types.erasure(typeCastTreeType);
  if (ASTHelpers.isSameType(erasedType,state.getSymtab().objectType,state)) {
    return Description.NO_MATCH;
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String typeSource=SuggestedFixes.qualifyType(state,fix,erasedType);
  fix.postfixWith(classForName,".asSubclass(" + typeSource + ".class)");
  if (types.isSameType(typeCastTreeType,erasedType)) {
    fix.replace(((JCTree)typeCastTree).getStartPosition(),((JCTree)typeCastTree.getExpression()).getStartPosition(),"");
  }
  return describeMatch(classForName,fix.build());
}
