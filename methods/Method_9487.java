private void setDoubleTapEnabled(boolean value){
  doubleTapEnabled=value;
  gestureDetector.setOnDoubleTapListener(value ? this : null);
}
