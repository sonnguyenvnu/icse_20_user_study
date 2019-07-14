private static Transition buildRenderThreadTransition(){
  final Transition[] pieces=new Transition[1 + RED_KEYS.length];
  pieces[0]=Transition.create(Transition.TransitionKeyType.GLOBAL,GRAY_KEYS).animate(AnimatedProperties.ALPHA).animator(Transition.renderThread(FADE_IN_OUT_DURATION)).disappearTo(0);
  int delay=FADE_IN_DELAY;
  for (int i=0; i < RED_KEYS.length; i++, delay+=FADE_IN_STAGGER_DELAY) {
    pieces[i + 1]=Transition.create(Transition.TransitionKeyType.GLOBAL,RED_KEYS[i]).animate(AnimatedProperties.ALPHA).animator(Transition.renderThread(delay,FADE_IN_OUT_DURATION)).appearFrom(0);
  }
  return Transition.parallel(pieces);
}
