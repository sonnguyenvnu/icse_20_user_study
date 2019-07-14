private void configureSharedElements(@NonNull ViewGroup container,@NonNull final View nonExistentView,@Nullable final View to,@Nullable View from,final boolean isPush,@NonNull final List<View> fromSharedElements,@NonNull final List<View> toSharedElements){
  if (to == null || from == null) {
    return;
  }
  ArrayMap<String,View> capturedFromSharedElements=captureFromSharedElements(from);
  if (sharedElementNames.isEmpty()) {
    sharedElementTransition=null;
  }
 else   if (capturedFromSharedElements != null) {
    fromSharedElements.addAll(capturedFromSharedElements.values());
  }
  if (enterTransition == null && exitTransition == null && sharedElementTransition == null) {
    return;
  }
  callSharedElementStartEnd(capturedFromSharedElements,true);
  final Rect toEpicenter;
  if (sharedElementTransition != null) {
    toEpicenter=new Rect();
    TransitionUtils.setTargets(sharedElementTransition,nonExistentView,fromSharedElements);
    setFromEpicenter(capturedFromSharedElements);
    if (enterTransition != null) {
      enterTransition.setEpicenterCallback(new Transition.EpicenterCallback(){
        @Override public Rect onGetEpicenter(        Transition transition){
          if (toEpicenter.isEmpty()) {
            return null;
          }
          return toEpicenter;
        }
      }
);
    }
  }
 else {
    toEpicenter=null;
  }
  OneShotPreDrawListener.add(true,container,new Runnable(){
    @Override public void run(){
      ArrayMap<String,View> capturedToSharedElements=captureToSharedElements(to,isPush);
      if (capturedToSharedElements != null) {
        toSharedElements.addAll(capturedToSharedElements.values());
        toSharedElements.add(nonExistentView);
      }
      callSharedElementStartEnd(capturedToSharedElements,false);
      if (sharedElementTransition != null) {
        sharedElementTransition.getTargets().clear();
        sharedElementTransition.getTargets().addAll(toSharedElements);
        TransitionUtils.replaceTargets(sharedElementTransition,fromSharedElements,toSharedElements);
        final View toEpicenterView=getToEpicenterView(capturedToSharedElements);
        if (toEpicenterView != null && toEpicenter != null) {
          TransitionUtils.getBoundsOnScreen(toEpicenterView,toEpicenter);
        }
      }
    }
  }
);
}
