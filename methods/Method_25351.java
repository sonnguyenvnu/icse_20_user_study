@Override protected Iterable<? extends MethodTree> getChildNodes(ClassTree classTree,VisitorState state){
  ImmutableList.Builder<MethodTree> result=ImmutableList.builder();
  for (  Tree member : classTree.getMembers()) {
    if (member instanceof MethodTree && ASTHelpers.getSymbol(member).isConstructor()) {
      result.add((MethodTree)member);
    }
  }
  return result.build();
}
