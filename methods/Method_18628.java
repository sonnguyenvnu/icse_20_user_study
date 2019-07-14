static void addTransitions(Transition transition,List<Transition> outList,@Nullable String logContext){
  if (transition instanceof Transition.BaseTransitionUnitsBuilder) {
    outList.addAll(((Transition.BaseTransitionUnitsBuilder)transition).getTransitionUnits());
  }
 else   if (transition != null) {
    outList.add(transition);
  }
 else {
    throw new IllegalStateException("[" + logContext + "] Adding null to transition list is not allowed.");
  }
}
