private static void setViewStateListAnimator(View view,ViewNodeInfo viewNodeInfo){
  StateListAnimator stateListAnimator=viewNodeInfo.getStateListAnimator();
  final int stateListAnimatorRes=viewNodeInfo.getStateListAnimatorRes();
  if (stateListAnimator == null && stateListAnimatorRes == 0) {
    return;
  }
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    throw new IllegalStateException("MountState has a ViewNodeInfo with stateListAnimator, " + "however the current Android version doesn't support stateListAnimator on Views");
  }
  if (stateListAnimator == null) {
    stateListAnimator=AnimatorInflater.loadStateListAnimator(view.getContext(),stateListAnimatorRes);
  }
  view.setStateListAnimator(stateListAnimator);
}
