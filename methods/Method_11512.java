/** 
 * Set the collapsed panel height in pixels
 * @param val A height in pixels
 */
public void setPanelHeight(int val){
  if (getPanelHeight() == val) {
    return;
  }
  mPanelHeight=val;
  if (!mFirstLayout) {
    requestLayout();
  }
  if (getPanelState() == PanelState.COLLAPSED) {
    smoothToBottom();
    invalidate();
    return;
  }
}
