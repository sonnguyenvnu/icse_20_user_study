@Nullable public static <T>PointFAnimator ofPointF(@Nullable T target,@Nullable PointFProperty<T> property,float startLeft,float startTop,float endLeft,float endTop){
  PointFAnimator animator=null;
  if (target != null && property != null) {
    animator=new PointFAnimator(target,property);
    animator.mStartLeft=startLeft;
    animator.mStartTop=startTop;
    animator.mEndLeft=endLeft;
    animator.mEndTop=endTop;
  }
  return animator;
}
