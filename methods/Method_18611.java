@Nullable static Transition getRootTransition(List<Transition> allTransitions){
  if (allTransitions.isEmpty()) {
    return null;
  }
  if (allTransitions.size() == 1) {
    return allTransitions.get(0);
  }
  return new ParallelTransitionSet(allTransitions);
}
