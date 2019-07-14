@Override protected void handleControlPropertyChanged(String p){
  super.handleControlPropertyChanged(p);
  if ("VALUE_FACTORY".equals(p)) {
    refreshSliderValueBinding();
  }
}
