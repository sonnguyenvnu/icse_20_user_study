@Override public Choice<UnifierWithUnconsumedStatements> apply(final UnifierWithUnconsumedStatements state){
  int goodIndex=0;
  while (goodIndex < state.unconsumedStatements().size()) {
    StatementTree stmt=state.unconsumedStatements().get(goodIndex);
    if (firstNonNull(FORBIDDEN_REFERENCE_SCANNER.scan(stmt,state.unifier()),false) || ControlFlowVisitor.INSTANCE.visitStatement(stmt) != ControlFlowVisitor.Result.NEVER_EXITS) {
      break;
    }
 else {
      goodIndex++;
    }
  }
  Collection<Integer> breakPoints=ContiguousSet.create(Range.closed(0,goodIndex),DiscreteDomain.integers());
  return Choice.from(breakPoints).transform((  Integer k) -> {
    Unifier unifier=state.unifier().fork();
    unifier.putBinding(key(),state.unconsumedStatements().subList(0,k));
    List<? extends StatementTree> remaining=state.unconsumedStatements().subList(k,state.unconsumedStatements().size());
    return UnifierWithUnconsumedStatements.create(unifier,remaining);
  }
);
}
