@Override @NonNull protected Animator getAnimator(@NonNull ViewGroup container,View from,View to,boolean isPush,boolean toAddedToContainer){
  AnimatorSet animator=new AnimatorSet();
  if (to != null) {
    float start=toAddedToContainer ? 0 : to.getAlpha();
    animator.play(ObjectAnimator.ofFloat(to,View.ALPHA,start,1));
  }
  if (from != null) {
    animator.play(ObjectAnimator.ofFloat(from,View.ALPHA,0));
    animator.play(ObjectAnimator.ofFloat(from,View.SCALE_X,0.8f));
    animator.play(ObjectAnimator.ofFloat(from,View.SCALE_Y,0.8f));
  }
  return animator;
}
