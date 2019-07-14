@Override public final void performChange(@NonNull final ViewGroup container,@Nullable final View from,@Nullable final View to,final boolean isPush,@NonNull final ControllerChangeCompletedListener changeListener){
  boolean readyToAnimate=true;
  final boolean addingToView=to != null && to.getParent() == null;
  if (addingToView) {
    if (isPush || from == null) {
      container.addView(to);
    }
 else     if (to.getParent() == null) {
      container.addView(to,container.indexOfChild(from));
    }
    if (to.getWidth() <= 0 && to.getHeight() <= 0) {
      readyToAnimate=false;
      onAnimationReadyOrAbortedListener=new OnAnimationReadyOrAbortedListener(container,from,to,isPush,true,changeListener);
      to.getViewTreeObserver().addOnPreDrawListener(onAnimationReadyOrAbortedListener);
    }
  }
  if (readyToAnimate) {
    performAnimation(container,from,to,isPush,addingToView,changeListener);
  }
}
