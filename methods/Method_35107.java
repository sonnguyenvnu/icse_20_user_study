private void scheduleTargetChange(@NonNull final ViewGroup container,@Nullable final View to,@NonNull final View nonExistentView,@NonNull final List<View> toSharedElements,@NonNull final List<View> enteringViews,@Nullable final List<View> exitingViews){
  OneShotPreDrawListener.add(true,container,new Runnable(){
    @Override public void run(){
      if (enterTransition != null) {
        enterTransition.removeTarget(nonExistentView);
        List<View> views=configureEnteringExitingViews(enterTransition,to,toSharedElements,nonExistentView);
        enteringViews.addAll(views);
      }
      if (exitingViews != null) {
        if (exitTransition != null) {
          List<View> tempExiting=new ArrayList<>();
          tempExiting.add(nonExistentView);
          TransitionUtils.replaceTargets(exitTransition,exitingViews,tempExiting);
        }
        exitingViews.clear();
        exitingViews.add(nonExistentView);
      }
    }
  }
);
}
