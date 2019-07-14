@Override @NonNull protected Animator getAnimator(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush,boolean toAddedToContainer){
  AnimatorSet animatorSet=new AnimatorSet();
  if (isPush) {
    if (from != null) {
      animatorSet.play(ObjectAnimator.ofFloat(from,View.TRANSLATION_X,-from.getWidth()));
    }
    if (to != null) {
      animatorSet.play(ObjectAnimator.ofFloat(to,View.TRANSLATION_X,to.getWidth(),0));
    }
  }
 else {
    if (from != null) {
      animatorSet.play(ObjectAnimator.ofFloat(from,View.TRANSLATION_X,from.getWidth()));
    }
    if (to != null) {
      float fromLeft=from != null ? from.getTranslationX() : 0;
      animatorSet.play(ObjectAnimator.ofFloat(to,View.TRANSLATION_X,fromLeft - to.getWidth(),0));
    }
  }
  return animatorSet;
}
