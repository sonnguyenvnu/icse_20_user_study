private void fixLayout(){
  ViewTreeObserver obs=fragmentView.getViewTreeObserver();
  obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
    @Override public boolean onPreDraw(){
      if (fragmentView == null) {
        return true;
      }
      fragmentView.getViewTreeObserver().removeOnPreDrawListener(this);
      WindowManager manager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Context.WINDOW_SERVICE);
      int rotation=manager.getDefaultDisplay().getRotation();
      if (rotation == Surface.ROTATION_270 || rotation == Surface.ROTATION_90) {
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
      }
 else {
        linearLayout.setOrientation(LinearLayout.VERTICAL);
      }
      fragmentView.setPadding(fragmentView.getPaddingLeft(),0,fragmentView.getPaddingRight(),fragmentView.getPaddingBottom());
      return true;
    }
  }
);
}
