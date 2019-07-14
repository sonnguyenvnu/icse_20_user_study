@Override public Description matchClass(final ClassTree tree,final VisitorState state){
  final ClassSymbol currentClass=ASTHelpers.getSymbol(tree);
  if (currentClass == null || !currentClass.hasOuterInstance()) {
    return NO_MATCH;
  }
  if (currentClass.getNestingKind() != NestingKind.MEMBER) {
    return NO_MATCH;
  }
switch (currentClass.owner.enclClass().getNestingKind()) {
case TOP_LEVEL:
    break;
case MEMBER:
  if (currentClass.owner.enclClass().hasOuterInstance()) {
    return NO_MATCH;
  }
break;
case LOCAL:
case ANONYMOUS:
return NO_MATCH;
}
if (tree.getExtendsClause() != null && ASTHelpers.getType(tree.getExtendsClause()).tsym.hasOuterInstance()) {
return NO_MATCH;
}
if (CanBeStaticAnalyzer.referencesOuter(tree,currentClass,state)) {
return NO_MATCH;
}
return describeMatch(tree,SuggestedFixes.addModifiers(tree,state,Modifier.STATIC));
}
