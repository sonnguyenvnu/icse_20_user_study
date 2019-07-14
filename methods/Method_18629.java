static void setOwnerKey(Transition transition,@Nullable String ownerKey){
  if (transition instanceof Transition.TransitionUnit) {
    ((Transition.TransitionUnit)transition).setOwnerKey(ownerKey);
  }
 else   if (transition instanceof TransitionSet) {
    ArrayList<Transition> children=((TransitionSet)transition).getChildren();
    for (int index=children.size() - 1; index >= 0; index--) {
      setOwnerKey(children.get(index),ownerKey);
    }
  }
 else   if (transition instanceof Transition.BaseTransitionUnitsBuilder) {
    ArrayList<Transition.TransitionUnit> units=((Transition.BaseTransitionUnitsBuilder)transition).getTransitionUnits();
    for (int index=units.size() - 1; index >= 0; index--) {
      units.get(index).setOwnerKey(ownerKey);
    }
  }
 else {
    throw new RuntimeException("Unhandled transition type: " + transition);
  }
}
