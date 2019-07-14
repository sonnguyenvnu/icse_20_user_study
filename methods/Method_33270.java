/** 
 * * Public API                                                               *
 */
@Override protected void layoutChildren(final double x,final double y,final double w,final double h){
  super.layoutChildren(x,y,w,h);
  final double height=getSkinnable().getHeight();
  linesWrapper.layoutLines(x,y,w,h,height,promptText == null ? 0 : snapPosition(promptText.getBaselineOffset() + promptText.getLayoutBounds().getHeight() * .36));
  errorContainer.layoutPane(x,height + linesWrapper.focusedLine.getHeight(),w,h);
  linesWrapper.updateLabelFloatLayout();
  if (invalid) {
    invalid=false;
    errorContainer.invalid(w);
    linesWrapper.invalid();
  }
}
