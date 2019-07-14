@Nullable @Override public Animator onAppear(@NonNull ViewGroup sceneRoot,@NonNull View view,@Nullable TransitionValues startValues,@Nullable TransitionValues endValues){
  if (endValues == null) {
    return null;
  }
  Rect bounds=(Rect)endValues.values.get(PROPNAME_SCREEN_BOUNDS);
  float endX=view.getTranslationX();
  float endY=view.getTranslationY();
  calculateOut(sceneRoot,bounds,mTempLoc);
  float startX=endX + mTempLoc[0];
  float startY=endY + mTempLoc[1];
  return TranslationAnimationCreator.createAnimation(view,endValues,bounds.left,bounds.top,startX,startY,endX,endY,sDecelerate,this);
}
