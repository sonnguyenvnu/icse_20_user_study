@OnUpdateStateWithTransition static Transition updateExpandedState(StateValue<Boolean> expanded,@Param boolean expand){
  expanded.set(expand);
  return Transition.parallel(Transition.allLayout(),Transition.create(Transition.TransitionKeyType.GLOBAL,ExpandableElementUtil.TRANSITION_TOP_DETAIL).animate(AnimatedProperties.HEIGHT).appearFrom(0).disappearTo(0),Transition.create(Transition.TransitionKeyType.GLOBAL,ExpandableElementUtil.TRANSITION_BOTTOM_DETAIL).animate(AnimatedProperties.HEIGHT).appearFrom(0).disappearTo(0));
}
