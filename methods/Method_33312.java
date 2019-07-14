@Override protected void handleControlPropertyChanged(String p){
  super.handleControlPropertyChanged(p);
  if ("SECONDARY_PROGRESS".equals(p)) {
    updateSecondaryProgress();
  }
 else   if ("VISIBLE".equals(p)) {
    updateAnimation();
  }
 else   if ("PARENT".equals(p)) {
    updateAnimation();
  }
 else   if ("SCENE".equals(p)) {
    updateAnimation();
  }
}
