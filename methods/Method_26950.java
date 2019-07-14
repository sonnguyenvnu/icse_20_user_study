private void captureValues(@NonNull TransitionValues transitionValues,int forcedVisibility){
  int visibility;
  if (forcedVisibility != -1) {
    visibility=forcedVisibility;
  }
 else {
    visibility=transitionValues.view.getVisibility();
  }
  transitionValues.values.put(PROPNAME_VISIBILITY,visibility);
  transitionValues.values.put(PROPNAME_PARENT,transitionValues.view.getParent());
  int[] loc=new int[2];
  transitionValues.view.getLocationOnScreen(loc);
  transitionValues.values.put(PROPNAME_SCREEN_LOCATION,loc);
}
