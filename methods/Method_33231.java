@Override protected void handleControlPropertyChanged(String p){
  super.handleControlPropertyChanged(p);
  if ("CHECKED_COLOR".equals(p)) {
    select.stop();
    createFillTransition();
    updateColors();
  }
 else   if ("UNCHECKED_COLOR".equals(p)) {
    updateColors();
  }
}
