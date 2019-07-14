/** 
 * Find the optimal permutation of arguments for this method invocation or return  {@code Changes.empty()} if no good permutations were found.
 */
Changes findChanges(InvocationInfo invocationInfo){
  if (invocationInfo.formalParameters().size() <= 1) {
    return Changes.empty();
  }
  if (invocationInfo.actualParameters().size() < invocationInfo.formalParameters().size()) {
    return Changes.empty();
  }
  ImmutableList<Parameter> formals=Parameter.createListFromVarSymbols(invocationInfo.formalParameters());
  ImmutableList<Parameter> actuals=Parameter.createListFromExpressionTrees(invocationInfo.actualParameters().subList(0,invocationInfo.formalParameters().size()));
  Costs costs=new Costs(formals,actuals);
  costs.viablePairs().filter(ParameterPair::isAlternativePairing).filter(p -> !p.actual().isAssignableTo(p.formal(),invocationInfo.state())).forEach(p -> costs.invalidatePair(p));
  if (costs.viablePairs().noneMatch(ParameterPair::isAlternativePairing)) {
    return Changes.empty();
  }
  costs.viablePairs().forEach(p -> costs.updatePair(p,distanceFunction().apply(p)));
  Changes changes=costs.computeAssignments();
  if (changes.isEmpty()) {
    return changes;
  }
  for (  Heuristic heuristic : heuristics()) {
    if (!heuristic.isAcceptableChange(changes,invocationInfo.tree(),invocationInfo.symbol(),invocationInfo.state())) {
      return Changes.empty();
    }
  }
  return changes;
}
