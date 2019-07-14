@OnCreateTransition static Transition onCreateTransition(ComponentContext c){
  return Transition.stagger(200,Transition.create("red").animate(AnimatedProperties.Y),Transition.create("blue").animate(AnimatedProperties.Y),Transition.create("green").animate(AnimatedProperties.Y));
}
