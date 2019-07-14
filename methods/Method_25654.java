@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (!PARCELABLE_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  Symbol parcelableCreatorSymbol=state.getSymbolFromString("android.os.Parcelable$Creator");
  if (parcelableCreatorSymbol == null) {
    return Description.NO_MATCH;
  }
  ClassType classType=ASTHelpers.getType(tree);
  for (  Tree member : tree.getMembers()) {
    if (member.getKind() != Kind.VARIABLE) {
      continue;
    }
    VariableTree variableTree=(VariableTree)member;
    if (PARCELABLE_CREATOR_MATCHER.matches(variableTree,state)) {
      if (isVariableClassCreator(variableTree,state,classType,parcelableCreatorSymbol)) {
        return Description.NO_MATCH;
      }
    }
  }
  return describeMatch(tree);
}
