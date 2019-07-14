protected void clearViews(){
  if (fragmentView != null) {
    ViewGroup parent=(ViewGroup)fragmentView.getParent();
    if (parent != null) {
      try {
        onRemoveFromParent();
        parent.removeView(fragmentView);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    fragmentView=null;
  }
  if (actionBar != null) {
    ViewGroup parent=(ViewGroup)actionBar.getParent();
    if (parent != null) {
      try {
        parent.removeView(actionBar);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    actionBar=null;
  }
  parentLayout=null;
}
