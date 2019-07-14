/** 
 * Updates view. Rebuilds items and label if necessary, recalculate items sizes.
 */
private void updateView(){
  if (rebuildItems()) {
    calculateLayoutWidth(getWidth(),MeasureSpec.EXACTLY);
    layout(getWidth(),getHeight());
  }
}
