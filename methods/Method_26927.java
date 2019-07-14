@Nullable public static Animator mergeAnimators(@Nullable Animator animator1,@Nullable Animator animator2){
  if (animator1 == null) {
    return animator2;
  }
 else   if (animator2 == null) {
    return animator1;
  }
 else {
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.playTogether(animator1,animator2);
    return animatorSet;
  }
}
