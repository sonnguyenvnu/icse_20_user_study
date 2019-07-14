@Override @NonNull protected Animator getAnimator(@NonNull ViewGroup container,View from,View to,boolean isPush,boolean toAddedToContainer){
  AnimatorSet animatorSet=new AnimatorSet();
  if (to != null) {
    to.setAlpha(0);
    ObjectAnimator rotation=ObjectAnimator.ofFloat(to,flipDirection.property,flipDirection.inStartRotation,0).setDuration(animationDuration);
    rotation.setInterpolator(new AccelerateDecelerateInterpolator());
    animatorSet.play(rotation);
    Animator alpha=ObjectAnimator.ofFloat(to,View.ALPHA,1).setDuration(animationDuration / 2);
    alpha.setStartDelay(animationDuration / 3);
    animatorSet.play(alpha);
  }
  if (from != null) {
    ObjectAnimator rotation=ObjectAnimator.ofFloat(from,flipDirection.property,0,flipDirection.outEndRotation).setDuration(animationDuration);
    rotation.setInterpolator(new AccelerateDecelerateInterpolator());
    animatorSet.play(rotation);
    Animator alpha=ObjectAnimator.ofFloat(from,View.ALPHA,0).setDuration(animationDuration / 2);
    alpha.setStartDelay(animationDuration / 3);
    animatorSet.play(alpha);
  }
  return animatorSet;
}
