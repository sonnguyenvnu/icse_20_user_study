private void addChild(Transition child){
  if (child instanceof Transition.BaseTransitionUnitsBuilder) {
    final ArrayList<? extends Transition> transitions=((Transition.BaseTransitionUnitsBuilder)child).getTransitionUnits();
    if (transitions.size() > 1) {
      mChildren.add(new ParallelTransitionSet(transitions));
    }
 else {
      mChildren.add(transitions.get(0));
    }
  }
 else   if (child != null) {
    mChildren.add(child);
  }
 else {
    throw new IllegalStateException("Null element is not allowed in transition set");
  }
}
