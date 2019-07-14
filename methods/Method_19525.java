@Nullable @OnCreateTransition static Transition onCreateTransition(ComponentContext c,@State Boolean expanded,@Prop(optional=true) boolean forceAnimateOnAppear){
  if (!forceAnimateOnAppear && expanded == null) {
    return null;
  }
  return Transition.parallel(Transition.allLayout(),Transition.create(Transition.TransitionKeyType.GLOBAL,TRANSITION_MSG_PARENT).animate(AnimatedProperties.HEIGHT).appearFrom(0),Transition.create(Transition.TransitionKeyType.GLOBAL,ExpandableElementUtil.TRANSITION_TOP_DETAIL).animate(AnimatedProperties.HEIGHT).appearFrom(0).disappearTo(0),Transition.create(Transition.TransitionKeyType.GLOBAL,ExpandableElementUtil.TRANSITION_BOTTOM_DETAIL).animate(AnimatedProperties.HEIGHT).appearFrom(0).disappearTo(0));
}
