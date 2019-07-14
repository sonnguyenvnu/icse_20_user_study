private static Function<Unifier,Choice<Unifier>> unifyUStatementWithSingleStatement(@Nullable final UStatement toUnify,@Nullable final StatementTree target){
  return (  Unifier unifier) -> {
    if (toUnify == null) {
      return (target == null) ? Choice.of(unifier) : Choice.<Unifier>none();
    }
    List<StatementTree> list=(target == null) ? List.<StatementTree>nil() : List.of(target);
    return toUnify.apply(UnifierWithUnconsumedStatements.create(unifier,list)).condition(s -> s.unconsumedStatements().isEmpty()).transform(UnifierWithUnconsumedStatements::unifier);
  }
;
}
