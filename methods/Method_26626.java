static Choice<Unifier> unifyStatementList(Iterable<? extends UStatement> statements,Iterable<? extends StatementTree> targets,Unifier unifier){
  Choice<UnifierWithUnconsumedStatements> choice=Choice.of(UnifierWithUnconsumedStatements.create(unifier,ImmutableList.copyOf(targets)));
  for (  UStatement statement : statements) {
    choice=choice.thenChoose(statement);
  }
  return choice.thenOption((  UnifierWithUnconsumedStatements state) -> state.unconsumedStatements().isEmpty() ? Optional.of(state.unifier()) : Optional.<Unifier>absent());
}
