private void redraw(final int count){
  if (count < 6) {
    if (containerView != null) {
      containerView.invalidate();
      AndroidUtilities.runOnUIThread(() -> redraw(count + 1),100);
    }
  }
}
