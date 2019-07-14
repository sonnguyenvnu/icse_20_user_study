public void fixLayout(){
  if (!AndroidUtilities.isTablet()) {
    return;
  }
  if (actionBarLayout == null) {
    return;
  }
  actionBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
    @Override public void onGlobalLayout(){
      needLayout();
      if (actionBarLayout != null) {
        actionBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
      }
    }
  }
);
}
