@Override @Nullable public Choice<UnifierWithUnconsumedStatements> apply(UnifierWithUnconsumedStatements state){
  java.util.List<? extends StatementTree> unconsumedStatements=state.unconsumedStatements();
  if (unconsumedStatements.isEmpty()) {
    return Choice.none();
  }
  final java.util.List<? extends StatementTree> unconsumedStatementsTail=unconsumedStatements.subList(1,unconsumedStatements.size());
  StatementTree firstStatement=unconsumedStatements.get(0);
  if (firstStatement.getKind() != Kind.IF) {
    return Choice.none();
  }
  final IfTree ifTree=(IfTree)firstStatement;
  Unifier unifier=state.unifier();
  Choice<UnifierWithUnconsumedStatements> forwardMatch=getCondition().unify(ifTree.getCondition(),unifier.fork()).thenChoose(unifyUStatementWithSingleStatement(getThenStatement(),ifTree.getThenStatement())).thenChoose(unifierAfterThen -> {
    if (getElseStatement() != null && ifTree.getElseStatement() == null && ControlFlowVisitor.INSTANCE.visitStatement(ifTree.getThenStatement()) == Result.ALWAYS_RETURNS) {
      Choice<UnifierWithUnconsumedStatements> result=getElseStatement().apply(UnifierWithUnconsumedStatements.create(unifierAfterThen.fork(),unconsumedStatementsTail));
      if (getElseStatement() instanceof UBlock) {
        Choice<UnifierWithUnconsumedStatements> alternative=Choice.of(UnifierWithUnconsumedStatements.create(unifierAfterThen.fork(),unconsumedStatementsTail));
        for (        UStatement stmt : ((UBlock)getElseStatement()).getStatements()) {
          alternative=alternative.thenChoose(stmt);
        }
        result=result.or(alternative);
      }
      return result;
    }
 else {
      return unifyUStatementWithSingleStatement(getElseStatement(),ifTree.getElseStatement()).apply(unifierAfterThen).transform(unifierAfterElse -> UnifierWithUnconsumedStatements.create(unifierAfterElse,unconsumedStatementsTail));
    }
  }
);
  Choice<UnifierWithUnconsumedStatements> backwardMatch=getCondition().negate().unify(ifTree.getCondition(),unifier.fork()).thenChoose(unifierAfterCond -> {
    if (getElseStatement() == null) {
      return Choice.none();
    }
    return getElseStatement().apply(UnifierWithUnconsumedStatements.create(unifierAfterCond,List.of(ifTree.getThenStatement()))).thenOption((    UnifierWithUnconsumedStatements stateAfterThen) -> stateAfterThen.unconsumedStatements().isEmpty() ? Optional.of(stateAfterThen.unifier()) : Optional.<Unifier>absent());
  }
).thenChoose(unifierAfterThen -> {
    if (ifTree.getElseStatement() == null && ControlFlowVisitor.INSTANCE.visitStatement(ifTree.getThenStatement()) == Result.ALWAYS_RETURNS) {
      Choice<UnifierWithUnconsumedStatements> result=getThenStatement().apply(UnifierWithUnconsumedStatements.create(unifierAfterThen.fork(),unconsumedStatementsTail));
      if (getThenStatement() instanceof UBlock) {
        Choice<UnifierWithUnconsumedStatements> alternative=Choice.of(UnifierWithUnconsumedStatements.create(unifierAfterThen.fork(),unconsumedStatementsTail));
        for (        UStatement stmt : ((UBlock)getThenStatement()).getStatements()) {
          alternative=alternative.thenChoose(stmt);
        }
        result=result.or(alternative);
      }
      return result;
    }
 else {
      return unifyUStatementWithSingleStatement(getThenStatement(),ifTree.getElseStatement()).apply(unifierAfterThen).transform(unifierAfterElse -> UnifierWithUnconsumedStatements.create(unifierAfterElse,unconsumedStatementsTail));
    }
  }
);
  return forwardMatch.or(backwardMatch);
}
