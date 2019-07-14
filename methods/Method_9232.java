@Override public void onConfigurationChanged(android.content.res.Configuration newConfig){
  super.onConfigurationChanged(newConfig);
  if (listView != null) {
    ViewTreeObserver obs=listView.getViewTreeObserver();
    obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        listView.getViewTreeObserver().removeOnPreDrawListener(this);
        fixLayoutInternal();
        return true;
      }
    }
);
  }
}
