@Override public Animator onDisappear(@NonNull ViewGroup sceneRoot,@NonNull View view,@Nullable TransitionValues startValues,@Nullable TransitionValues endValues){
  if (startValues == null) {
    return null;
  }
  int[] position=(int[])startValues.values.get(PROPNAME_SCREEN_LOCATION);
  float startX=view.getTranslationX();
  float startY=view.getTranslationY();
  float endX=mSlideCalculator.getGoneX(sceneRoot,view);
  float endY=mSlideCalculator.getGoneY(sceneRoot,view);
  return TranslationAnimationCreator.createAnimation(view,startValues,position[0],position[1],startX,startY,endX,endY,sAccelerate,this);
}
