void configureTransition(@NonNull final ViewGroup container,@Nullable View from,@Nullable View to,@NonNull final Transition transition,boolean isPush){
  final View nonExistentView=new View(container.getContext());
  List<View> fromSharedElements=new ArrayList<>();
  List<View> toSharedElements=new ArrayList<>();
  configureSharedElements(container,nonExistentView,to,from,isPush,fromSharedElements,toSharedElements);
  List<View> exitingViews=exitTransition != null ? configureEnteringExitingViews(exitTransition,from,fromSharedElements,nonExistentView) : null;
  if (exitingViews == null || exitingViews.isEmpty()) {
    exitTransition=null;
  }
  if (enterTransition != null) {
    enterTransition.addTarget(nonExistentView);
  }
  final List<View> enteringViews=new ArrayList<>();
  scheduleRemoveTargets(transition,enterTransition,enteringViews,exitTransition,exitingViews,sharedElementTransition,toSharedElements);
  scheduleTargetChange(container,to,nonExistentView,toSharedElements,enteringViews,exitingViews);
  setNameOverrides(container,toSharedElements);
  scheduleNameReset(container,toSharedElements);
}
