@NonNull List<View> configureEnteringExitingViews(@NonNull Transition transition,@Nullable View view,@NonNull List<View> sharedElements,@NonNull View nonExistentView){
  List<View> viewList=new ArrayList<>();
  if (view != null) {
    captureTransitioningViews(viewList,view);
  }
  viewList.removeAll(sharedElements);
  if (!viewList.isEmpty()) {
    viewList.add(nonExistentView);
    TransitionUtils.addTargets(transition,viewList);
  }
  return viewList;
}
