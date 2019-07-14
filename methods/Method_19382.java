@Nullable public static SnapHelper getSnapHelper(@SnapMode int snapMode,int deltaJumpThreshold){
switch (snapMode) {
case SNAP_TO_CENTER:
    return new PagerSnapHelper();
case SNAP_TO_START:
  return new StartSnapHelper();
case SNAP_TO_CENTER_CHILD:
return new LinearSnapHelper();
case SNAP_TO_CENTER_CHILD_WITH_CUSTOM_SPEED:
return new CustomSpeedLinearSnapHelper(deltaJumpThreshold);
case SNAP_TO_END:
case SNAP_NONE:
default :
return null;
}
}
