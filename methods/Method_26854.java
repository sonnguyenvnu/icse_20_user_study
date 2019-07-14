@Nullable private Animator createAnimation(@NonNull final View view,float startScale,float endScale,@Nullable TransitionValues values){
  final float initialScaleX=view.getScaleX();
  final float initialScaleY=view.getScaleY();
  float startScaleX=initialScaleX * startScale;
  float endScaleX=initialScaleX * endScale;
  float startScaleY=initialScaleY * startScale;
  float endScaleY=initialScaleY * endScale;
  if (values != null) {
    Float savedScaleX=(Float)values.values.get(PROPNAME_SCALE_X);
    Float savedScaleY=(Float)values.values.get(PROPNAME_SCALE_Y);
    if (savedScaleX != null && savedScaleX != initialScaleX) {
      startScaleX=savedScaleX;
    }
    if (savedScaleY != null && savedScaleY != initialScaleY) {
      startScaleY=savedScaleY;
    }
  }
  view.setScaleX(startScaleX);
  view.setScaleY(startScaleY);
  Animator animator=TransitionUtils.mergeAnimators(ObjectAnimator.ofFloat(view,View.SCALE_X,startScaleX,endScaleX),ObjectAnimator.ofFloat(view,View.SCALE_Y,startScaleY,endScaleY));
  addListener(new TransitionListenerAdapter(){
    @Override public void onTransitionEnd(    @NonNull Transition transition){
      view.setScaleX(initialScaleX);
      view.setScaleY(initialScaleY);
      transition.removeListener(this);
    }
  }
);
  return animator;
}
