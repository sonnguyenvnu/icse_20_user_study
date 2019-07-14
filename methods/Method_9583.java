private void fixLayout(){
  if (fragmentView == null) {
    return;
  }
  fragmentView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
    @Override public boolean onPreDraw(){
      if (fragmentView != null) {
        checkListViewScroll();
        needLayout();
        fragmentView.getViewTreeObserver().removeOnPreDrawListener(this);
      }
      return true;
    }
  }
);
}
