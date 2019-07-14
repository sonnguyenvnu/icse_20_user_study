@Override public boolean isTransitionRequired(@Nullable TransitionValues startValues,@Nullable TransitionValues newValues){
  if (startValues == null && newValues == null) {
    return false;
  }
  if (startValues != null && newValues != null && newValues.values.containsKey(PROPNAME_VISIBILITY) != startValues.values.containsKey(PROPNAME_VISIBILITY)) {
    return false;
  }
  VisibilityInfo changeInfo=getVisibilityChangeInfo(startValues,newValues);
  return changeInfo.visibilityChange && (changeInfo.startVisibility == View.VISIBLE || changeInfo.endVisibility == View.VISIBLE);
}
