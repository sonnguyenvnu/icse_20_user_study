/** 
 * Set the draggable view portion. Use to null, to allow the whole panel to be draggable
 * @param dragView A view that will be used to drag the panel.
 */
public void setDragView(View dragView){
  if (mDragView != null) {
    mDragView.setOnClickListener(null);
  }
  mDragView=dragView;
  if (mDragView != null) {
    mDragView.setClickable(true);
    mDragView.setFocusable(false);
    mDragView.setFocusableInTouchMode(false);
    mDragView.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        if (!isEnabled() || !isTouchEnabled())         return;
        if (mSlideState != PanelState.EXPANDED && mSlideState != PanelState.ANCHORED) {
          if (mAnchorPoint < 1.0f) {
            setPanelState(PanelState.ANCHORED);
          }
 else {
            setPanelState(PanelState.EXPANDED);
          }
        }
 else {
          setPanelState(PanelState.COLLAPSED);
        }
      }
    }
);
    ;
  }
}
