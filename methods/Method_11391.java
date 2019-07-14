/** 
 * Force the view to call  {@link #requestLayout()} if the new text doesn't match the old boundswe set for the previous view state.
 */
private void checkForRelayout(){
  final boolean widthChanged=lastMeasuredDesiredWidth != computeDesiredWidth();
  final boolean heightChanged=lastMeasuredDesiredHeight != computeDesiredHeight();
  if (widthChanged || heightChanged) {
    requestLayout();
  }
}
