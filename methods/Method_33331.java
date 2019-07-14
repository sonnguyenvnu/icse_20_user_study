@Override protected void handleControlPropertyChanged(String p){
  super.handleControlPropertyChanged(p);
  if ("INDETERMINATE".equals(p)) {
    initialize();
  }
 else   if ("PROGRESS".equals(p)) {
    updateProgress();
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
