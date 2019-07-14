private void updateAnimation(){
  ProgressIndicator control=getSkinnable();
  final boolean isTreeVisible=control.isVisible() && control.getParent() != null && control.getScene() != null;
  if (timeline != null) {
    pauseTimeline(!isTreeVisible);
  }
 else   if (isTreeVisible) {
    createTransition();
  }
}
