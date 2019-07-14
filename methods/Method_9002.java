private void updateSelectorState(){
  if (selectorDrawable != null && selectorDrawable.isStateful()) {
    if (currentChildView != null) {
      if (selectorDrawable.setState(getDrawableStateForSelector())) {
        invalidateDrawable(selectorDrawable);
      }
    }
 else     if (removeHighlighSelectionRunnable == null) {
      selectorDrawable.setState(StateSet.NOTHING);
    }
  }
}
