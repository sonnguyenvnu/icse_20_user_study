@Override public Choice<Unifier> visitClass(ClassTree node,Unifier unifier){
  Choice<UnifierWithRemainingMembers> path=Choice.of(UnifierWithRemainingMembers.create(unifier,getMembers()));
  for (  Tree targetMember : node.getMembers()) {
    if (!(targetMember instanceof MethodTree) || ((MethodTree)targetMember).getReturnType() != null) {
      path=path.thenChoose(match(targetMember));
    }
  }
  return path.condition(s -> s.remainingMembers().isEmpty()).transform(UnifierWithRemainingMembers::unifier);
}
