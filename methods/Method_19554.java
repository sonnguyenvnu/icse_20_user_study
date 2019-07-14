private static Transition buildUiThreadTransition(){
  final Transition.TransitionAnimator fadeInOutAnimator=Transition.timing(FADE_IN_OUT_DURATION);
  final Transition fadeOut=Transition.create(Transition.TransitionKeyType.GLOBAL,GRAY_KEYS).animate(AnimatedProperties.ALPHA).animator(fadeInOutAnimator).disappearTo(0);
  final Transition[] fadeInPieces=new Transition[RED_KEYS.length];
  for (int i=0; i < RED_KEYS.length; i++) {
    fadeInPieces[i]=Transition.create(Transition.TransitionKeyType.GLOBAL,RED_KEYS[i]).animate(AnimatedProperties.ALPHA).animator(fadeInOutAnimator).appearFrom(0);
  }
  final Transition fadeIn=Transition.stagger(FADE_IN_STAGGER_DELAY,fadeInPieces);
  return Transition.stagger(FADE_IN_DELAY,fadeOut,fadeIn);
}
