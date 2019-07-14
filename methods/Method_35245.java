@Nullable @Override public Transition getSharedElementTransition(@NonNull ViewGroup container,@Nullable final View from,@Nullable View to,boolean isPush){
  Transition transition=new TransitionSet().addTransition(new ChangeBounds()).addTransition(new ChangeClipBounds()).addTransition(new ChangeTransform());
  transition.setPathMotion(new ArcMotion());
  transition.addListener(new TransitionListener(){
    @Override public void onTransitionStart(    Transition transition){
      if (from != null) {
        for (        String name : sharedElementNames) {
          View namedView=TransitionUtils.findNamedView(from,name);
          if (namedView != null) {
            namedView.setVisibility(View.INVISIBLE);
          }
        }
      }
    }
    @Override public void onTransitionEnd(    Transition transition){
    }
    @Override public void onTransitionCancel(    Transition transition){
    }
    @Override public void onTransitionPause(    Transition transition){
    }
    @Override public void onTransitionResume(    Transition transition){
    }
  }
);
  return transition;
}
