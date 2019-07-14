@Override @NonNull protected Animator getAnimator(@NonNull ViewGroup container,View from,View to,boolean isPush,boolean toAddedToContainer){
  final float radius=(float)Math.hypot(cx,cy);
  Animator animator=null;
  if (isPush && to != null) {
    animator=ViewAnimationUtils.createCircularReveal(to,cx,cy,0,radius);
  }
 else   if (!isPush && from != null) {
    animator=ViewAnimationUtils.createCircularReveal(from,cx,cy,radius,0);
  }
  return animator;
}
