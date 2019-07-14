private static Function<UnifierWithRemainingMembers,Choice<UnifierWithRemainingMembers>> match(final Tree tree){
  return (  final UnifierWithRemainingMembers state) -> {
    final ImmutableList<UMethodDecl> currentMembers=state.remainingMembers();
    Choice<Integer> methodChoice=Choice.from(ContiguousSet.create(Range.closedOpen(0,currentMembers.size()),DiscreteDomain.integers()));
    return methodChoice.thenChoose((    Integer i) -> {
      ImmutableList<UMethodDecl> remainingMembers=ImmutableList.<UMethodDecl>builder().addAll(currentMembers.subList(0,i)).addAll(currentMembers.subList(i + 1,currentMembers.size())).build();
      UMethodDecl chosenMethod=currentMembers.get(i);
      Unifier unifier=state.unifier().fork();
      for (      UVariableDecl param : chosenMethod.getParameters()) {
        unifier.clearBinding(param.key());
      }
      return chosenMethod.unify(tree,unifier).transform(UnifierWithRemainingMembers.withRemaining(remainingMembers));
    }
);
  }
;
}
