private void toggle(boolean show,boolean animate){
  if (show && mFabWithLabelViews.isEmpty()) {
    show=false;
    if (mOnChangeListener != null) {
      mOnChangeListener.onMainActionSelected();
    }
  }
  if (isOpen() == show) {
    return;
  }
  mInstanceState.mIsOpen=show;
  visibilitySetup(show,animate,mInstanceState.mUseReverseAnimationOnClose);
  updateMainFabDrawable(animate);
  updateMainFabBackgroundColor();
  showHideOverlay(show,animate);
  if (mOnChangeListener != null) {
    mOnChangeListener.onToggleChanged(show);
  }
}
