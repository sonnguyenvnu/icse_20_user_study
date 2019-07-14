private void waitOnAllTransitionNames(@NonNull final View to,@NonNull final OnTransitionPreparedListener onTransitionPreparedListener){
  OnPreDrawListener onPreDrawListener=new OnPreDrawListener(){
    @Override public boolean onPreDraw(){
      List<View> foundViews=new ArrayList<>();
      boolean allViewsFound=true;
      for (      String transitionName : waitForTransitionNames) {
        View namedView=TransitionUtils.findNamedView(to,transitionName);
        if (namedView != null) {
          foundViews.add(TransitionUtils.findNamedView(to,transitionName));
        }
 else {
          allViewsFound=false;
          break;
        }
      }
      if (allViewsFound && !addedSubviewListeners) {
        addedSubviewListeners=true;
        waitOnChildTransitionNames(to,foundViews,this,onTransitionPreparedListener);
      }
      return false;
    }
  }
;
  to.getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
}
