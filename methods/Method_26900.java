@Override @Nullable public Animator onAppear(@NonNull ViewGroup sceneRoot,@NonNull View view,@Nullable TransitionValues startValues,@Nullable TransitionValues endValues){
  if (endValues == null) {
    return null;
  }
  int[] position=(int[])endValues.values.get(PROPNAME_SCREEN_LOCATION);
  float endX=view.getTranslationX();
  float endY=view.getTranslationY();
  float startX=mSlideCalculator.getGoneX(sceneRoot,view);
  float startY=mSlideCalculator.getGoneY(sceneRoot,view);
  return TranslationAnimationCreator.createAnimation(view,endValues,position[0],position[1],startX,startY,endX,endY,sDecelerate,this);
}
