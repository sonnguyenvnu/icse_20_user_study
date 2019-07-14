@OnCreateTransition static Transition onCreateTransition(ComponentContext c){
  return Transition.sequence(Transition.create("red").animate(AnimatedProperties.X),Transition.create("blue").animate(AnimatedProperties.X),Transition.create("green").animate(AnimatedProperties.X));
}
