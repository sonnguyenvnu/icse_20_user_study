@Override public void executePropertyChanges(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,@Nullable Transition transition,boolean isPush){
  if (isPush) {
    fabParent.removeView(fab);
    container.addView(to);
    AnimUtils.TransitionEndListener endListener=new AnimUtils.TransitionEndListener(){
      @Override public void onTransitionCompleted(      Transition transition){
        fab.setVisibility(View.GONE);
        fabParent.addView(fab);
        fab=null;
        fabParent=null;
      }
    }
;
    if (transition != null) {
      transition.addListener(endListener);
    }
 else {
      endListener.onTransitionCompleted(null);
    }
  }
 else {
    dialogBackground.setVisibility(View.INVISIBLE);
    fabParent.addView(fab);
    container.removeView(from);
    AnimUtils.TransitionEndListener endListener=new AnimUtils.TransitionEndListener(){
      @Override public void onTransitionCompleted(      Transition transition){
        fabParent.removeView(dialogBackground);
        dialogBackground=null;
      }
    }
;
    if (transition != null) {
      transition.addListener(endListener);
    }
 else {
      endListener.onTransitionCompleted(null);
    }
  }
}
