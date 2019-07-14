@Override public Choice<UnifierWithUnconsumedStatements> apply(final UnifierWithUnconsumedStatements initState){
  final PlaceholderUnificationVisitor visitor=PlaceholderUnificationVisitor.create(TreeMaker.instance(initState.unifier().getContext()),arguments());
  PlaceholderVerificationVisitor verification=new PlaceholderVerificationVisitor(Collections2.transform(placeholder().requiredParameters(),Functions.forMap(arguments())),arguments().values());
  Choice<State<ConsumptionState>> realOptions=Choice.none();
  Choice<State<ConsumptionState>> choiceToHere=Choice.of(State.create(List.<UVariableDecl>nil(),initState.unifier(),ConsumptionState.empty()));
  if (verification.allRequiredMatched()) {
    realOptions=choiceToHere.or(realOptions);
  }
  for (  final StatementTree targetStatement : initState.unconsumedStatements()) {
    if (!verification.scan(targetStatement,initState.unifier())) {
      break;
    }
    choiceToHere=choiceToHere.thenChoose((    final State<ConsumptionState> consumptionState) -> visitor.unifyStatement(targetStatement,consumptionState).transform((    State<? extends JCStatement> stmtState) -> stmtState.withResult(consumptionState.result().consume(stmtState.result()))));
    if (verification.allRequiredMatched()) {
      realOptions=choiceToHere.or(realOptions);
    }
  }
  return realOptions.thenOption((  State<ConsumptionState> consumptionState) -> {
    if (ImmutableSet.copyOf(consumptionState.seenParameters()).containsAll(placeholder().requiredParameters())) {
      Unifier resultUnifier=consumptionState.unifier().fork();
      int nConsumedStatements=consumptionState.result().consumedStatements();
      java.util.List<? extends StatementTree> remainingStatements=initState.unconsumedStatements().subList(nConsumedStatements,initState.unconsumedStatements().size());
      UnifierWithUnconsumedStatements result=UnifierWithUnconsumedStatements.create(resultUnifier,remainingStatements);
      List<JCStatement> impl=consumptionState.result().placeholderImplInReverseOrder().reverse();
      ControlFlowVisitor.Result implFlow=ControlFlowVisitor.INSTANCE.visitStatements(impl);
      if (implFlow == implementationFlow()) {
        List<JCStatement> prevBinding=resultUnifier.getBinding(placeholder().blockKey());
        if (prevBinding != null && prevBinding.toString().equals(impl.toString())) {
          return Optional.of(result);
        }
 else         if (prevBinding == null) {
          resultUnifier.putBinding(placeholder().blockKey(),impl);
          return Optional.of(result);
        }
      }
    }
    return Optional.absent();
  }
);
}
