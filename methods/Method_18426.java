private static void unsetViewStateListAnimator(View view,ViewNodeInfo viewNodeInfo){
  if (viewNodeInfo.getStateListAnimator() == null && viewNodeInfo.getStateListAnimatorRes() == 0) {
    return;
  }
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    throw new IllegalStateException("MountState has a ViewNodeInfo with stateListAnimator, " + "however the current Android version doesn't support stateListAnimator on Views");
  }
  view.setStateListAnimator(null);
}
