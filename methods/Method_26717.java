private static Function<Unifier,UnifierWithUnconsumedStatements> withUnconsumed(final java.util.List<? extends StatementTree> statements){
  return (  Unifier unifier) -> UnifierWithUnconsumedStatements.create(unifier,statements);
}
