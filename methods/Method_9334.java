@Override public void onConfigurationChanged(android.content.res.Configuration newConfig){
  super.onConfigurationChanged(newConfig);
  for (int a=0; a < mediaPages.length; a++) {
    if (mediaPages[a].listView != null) {
      final int num=a;
      ViewTreeObserver obs=mediaPages[a].listView.getViewTreeObserver();
      obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
        @Override public boolean onPreDraw(){
          mediaPages[num].getViewTreeObserver().removeOnPreDrawListener(this);
          fixLayoutInternal(num);
          return true;
        }
      }
);
    }
  }
}
