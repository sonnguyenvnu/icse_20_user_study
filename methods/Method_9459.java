private void fixLayout(){
  if (listView != null) {
    ViewTreeObserver obs=listView.getViewTreeObserver();
    obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        fixLayoutInternal();
        if (listView != null) {
          listView.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        return true;
      }
    }
);
  }
}
