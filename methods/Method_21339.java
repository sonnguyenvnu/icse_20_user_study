/** 
 * Adjust card content spacing when metadata label is present.
 */
private void adjustLandscapeTopPadding(final @Nullable ViewGroup landscapeViewGroup,final int left,final int top,final int right,final int bottom){
  if (landscapeViewGroup != null) {
    landscapeViewGroup.setPadding(left,top,right,bottom);
  }
}
