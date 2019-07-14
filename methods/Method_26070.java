private Description check(ExpressionTree condition,ImmutableList<Tree> loopBodyTrees){
  ImmutableSet<Symbol.VarSymbol> conditionVars=LoopConditionVisitor.scan(condition);
  if (conditionVars.isEmpty()) {
    return NO_MATCH;
  }
  for (  Tree tree : loopBodyTrees) {
    if (UpdateScanner.scan(tree,conditionVars)) {
      return NO_MATCH;
    }
  }
  return buildDescription(condition).setMessage(String.format("condition variable(s) never modified in loop body: %s",Joiner.on(", ").join(conditionVars))).build();
}
