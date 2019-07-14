private static SuggestedFix makeAssignmentDeclaration(Symbol unusedSymbol,Collection<UnusedSpec> specs,ImmutableList<TreePath> allUsageSites,VisitorState state){
  if (unusedSymbol.getKind() != ElementKind.LOCAL_VARIABLE) {
    return SuggestedFix.builder().build();
  }
  Optional<VariableTree> removedVariableTree=allUsageSites.stream().filter(tp -> tp.getLeaf() instanceof VariableTree).findFirst().map(tp -> (VariableTree)tp.getLeaf());
  Optional<AssignmentTree> reassignment=specs.stream().map(UnusedSpec::terminatingAssignment).filter(Optional::isPresent).map(Optional::get).filter(a -> allUsageSites.stream().noneMatch(tp -> tp.getLeaf().equals(a))).findFirst();
  if (!removedVariableTree.isPresent() || !reassignment.isPresent()) {
    return SuggestedFix.builder().build();
  }
  return SuggestedFix.prefixWith(reassignment.get(),state.getSourceForNode(removedVariableTree.get().getType()) + " ");
}
