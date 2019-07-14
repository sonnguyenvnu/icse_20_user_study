@OnCreateTransition static Transition onCreateTransition(ComponentContext c){
  return Transition.parallel(Transition.create("red").animate(AnimatedProperties.X).animator(Transition.timing(1000,new LinearInterpolator())),Transition.create("blue").animate(AnimatedProperties.X).animator(Transition.timing(1000)),Transition.create("green").animate(AnimatedProperties.X).animator(Transition.timing(1000,new BounceInterpolator())),Transition.delay(1000,Transition.create("black").animate(AnimatedProperties.X).animator(Transition.timing(1000))));
}
