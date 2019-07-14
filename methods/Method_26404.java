private static ImmutableList<Tree> getNearbyTreesToScan(VisitorState state){
  for (  Tree parent : state.getPath()) {
    if (parent.getKind() == Tree.Kind.CLASS) {
      ImmutableList.Builder<Tree> treesToScan=ImmutableList.builder();
      for (      Tree member : ((ClassTree)parent).getMembers()) {
        if (member instanceof VariableTree) {
          ExpressionTree expressionTree=((VariableTree)member).getInitializer();
          if (expressionTree != null) {
            treesToScan.add(expressionTree);
          }
        }
      }
      return treesToScan.build();
    }
    if (parent.getKind() == Tree.Kind.BLOCK) {
      return ImmutableList.of(parent);
    }
    if (parent.getKind() == Tree.Kind.LAMBDA_EXPRESSION) {
      return ImmutableList.of();
    }
  }
  return ImmutableList.of();
}
