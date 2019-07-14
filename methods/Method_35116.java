private void scheduleRemoveTargets(@NonNull final Transition overallTransition,@Nullable final Transition enterTransition,@Nullable final List<View> enteringViews,@Nullable final Transition exitTransition,@Nullable final List<View> exitingViews,@Nullable final Transition sharedElementTransition,@Nullable final List<View> toSharedElements){
  overallTransition.addListener(new TransitionListener(){
    @Override public void onTransitionStart(    Transition transition){
      if (enterTransition != null && enteringViews != null) {
        TransitionUtils.replaceTargets(enterTransition,enteringViews,null);
      }
      if (exitTransition != null && exitingViews != null) {
        TransitionUtils.replaceTargets(exitTransition,exitingViews,null);
      }
      if (sharedElementTransition != null && toSharedElements != null) {
        TransitionUtils.replaceTargets(sharedElementTransition,toSharedElements,null);
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
}
