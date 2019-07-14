private void positionSelector(int position,View sel,boolean manageHotspot,float x,float y){
  if (removeHighlighSelectionRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(removeHighlighSelectionRunnable);
    removeHighlighSelectionRunnable=null;
    pendingHighlightPosition=null;
  }
  if (selectorDrawable == null) {
    return;
  }
  final boolean positionChanged=position != selectorPosition;
  int bottomPadding;
  if (getAdapter() instanceof SelectionAdapter) {
    bottomPadding=((SelectionAdapter)getAdapter()).getSelectionBottomPadding(sel);
  }
 else {
    bottomPadding=0;
  }
  if (position != NO_POSITION) {
    selectorPosition=position;
  }
  selectorRect.set(sel.getLeft(),sel.getTop(),sel.getRight(),sel.getBottom() - bottomPadding);
  final boolean enabled=sel.isEnabled();
  if (isChildViewEnabled != enabled) {
    isChildViewEnabled=enabled;
  }
  if (positionChanged) {
    selectorDrawable.setVisible(false,false);
    selectorDrawable.setState(StateSet.NOTHING);
  }
  selectorDrawable.setBounds(selectorRect);
  if (positionChanged) {
    if (getVisibility() == VISIBLE) {
      selectorDrawable.setVisible(true,false);
    }
  }
  if (Build.VERSION.SDK_INT >= 21 && manageHotspot) {
    selectorDrawable.setHotspot(x,y);
  }
}
