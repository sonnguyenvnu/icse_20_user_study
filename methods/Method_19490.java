@OnCreateTransition static Transition onCreateTransition(ComponentContext c){
  return Transition.parallel(Transition.allLayout().animator(ANIMATOR),Transition.create(Transition.TransitionKeyType.GLOBAL,TRANSITION_KEY_TEXT).animate(AnimatedProperties.WIDTH).appearFrom(0f).disappearTo(0f).animator(ANIMATOR),Transition.create(Transition.TransitionKeyType.GLOBAL,TRANSITION_KEY_TEXT).animate(AnimatedProperties.ALPHA).appearFrom(0f).disappearTo(0f).animator(ANIMATOR));
}
