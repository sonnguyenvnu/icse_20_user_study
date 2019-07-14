public static void replaceTargets(@NonNull Transition transition,@NonNull List<View> oldTargets,@Nullable List<View> newTargets){
  if (transition instanceof TransitionSet) {
    TransitionSet set=(TransitionSet)transition;
    int numTransitions=set.getTransitionCount();
    for (int i=0; i < numTransitions; i++) {
      Transition child=set.getTransitionAt(i);
      replaceTargets(child,oldTargets,newTargets);
    }
  }
 else   if (!TransitionUtils.hasSimpleTarget(transition)) {
    List<View> targets=transition.getTargets();
    if (targets != null && targets.size() == oldTargets.size() && targets.containsAll(oldTargets)) {
      final int targetCount=newTargets == null ? 0 : newTargets.size();
      for (int i=0; i < targetCount; i++) {
        transition.addTarget(newTargets.get(i));
      }
      for (int i=oldTargets.size() - 1; i >= 0; i--) {
        transition.removeTarget(oldTargets.get(i));
      }
    }
  }
}
