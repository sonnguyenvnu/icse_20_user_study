/** 
 * Utility method to handle creating and running the Animator.
 */
private Animator createAnimation(final View view,float startAlpha,float endAlpha,@Nullable TransitionValues values){
  final float curAlpha=view.getAlpha();
  startAlpha=curAlpha * startAlpha;
  endAlpha=curAlpha * endAlpha;
  if (values != null && values.values.containsKey(PROPNAME_ALPHA)) {
    float savedAlpha=(Float)values.values.get(PROPNAME_ALPHA);
    if (savedAlpha != curAlpha) {
      startAlpha=savedAlpha;
    }
  }
  view.setAlpha(startAlpha);
  final ObjectAnimator anim=ObjectAnimator.ofFloat(view,View.ALPHA,endAlpha);
  final FadeAnimatorListener listener=new FadeAnimatorListener(view,curAlpha);
  anim.addListener(listener);
  addListener(new TransitionListenerAdapter(){
    @Override public void onTransitionEnd(    @NonNull Transition transition){
      view.setAlpha(curAlpha);
      transition.removeListener(this);
    }
  }
);
  return anim;
}
