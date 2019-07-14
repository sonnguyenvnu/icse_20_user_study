private static SmoothScrollAlignmentType getSmoothScrollAlignmentTypeFrom(int snapMode){
switch (snapMode) {
case SNAP_TO_START:
    return SmoothScrollAlignmentType.SNAP_TO_START;
case SNAP_TO_END:
  return SmoothScrollAlignmentType.SNAP_TO_END;
case SNAP_TO_CENTER:
case SNAP_TO_CENTER_CHILD:
return SmoothScrollAlignmentType.SNAP_TO_CENTER;
default :
return SmoothScrollAlignmentType.DEFAULT;
}
}
