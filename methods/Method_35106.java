void waitOnChildTransitionNames(@NonNull final View to,@NonNull List<View> foundViews,@NonNull final OnPreDrawListener parentPreDrawListener,@NonNull final OnTransitionPreparedListener onTransitionPreparedListener){
  for (  final View view : foundViews) {
    OneShotPreDrawListener.add(true,view,new Runnable(){
      @Override public void run(){
        waitForTransitionNames.remove(view.getTransitionName());
        removedViews.add(new ViewParentPair(view,(ViewGroup)view.getParent()));
        ((ViewGroup)view.getParent()).removeView(view);
        if (waitForTransitionNames.size() == 0) {
          to.getViewTreeObserver().removeOnPreDrawListener(parentPreDrawListener);
          to.setVisibility(View.INVISIBLE);
          onTransitionPreparedListener.onPrepared();
        }
      }
    }
);
  }
}
