static boolean targetsAllLayout(@Nullable Transition transition){
  if (transition == null) {
    return false;
  }
 else   if (transition instanceof TransitionSet) {
    ArrayList<Transition> children=((TransitionSet)transition).getChildren();
    for (int i=0, size=children.size(); i < size; i++) {
      if (targetsAllLayout(children.get(i))) {
        return true;
      }
    }
  }
 else   if (transition instanceof Transition.TransitionUnit) {
    final Transition.TransitionUnit unit=(Transition.TransitionUnit)transition;
    final Transition.ComponentTargetType targetType=unit.getAnimationTarget().componentTarget.componentTargetType;
    if (targetType == Transition.ComponentTargetType.ALL || targetType == Transition.ComponentTargetType.AUTO_LAYOUT) {
      return true;
    }
  }
 else   if (transition instanceof Transition.BaseTransitionUnitsBuilder) {
    final Transition.BaseTransitionUnitsBuilder builder=(Transition.BaseTransitionUnitsBuilder)transition;
    final List<Transition.TransitionUnit> units=builder.getTransitionUnits();
    for (int i=0, size=units.size(); i < size; i++) {
      if (targetsAllLayout(units.get(i))) {
        return true;
      }
    }
  }
 else {
    throw new RuntimeException("Unhandled transition type: " + transition);
  }
  return false;
}
