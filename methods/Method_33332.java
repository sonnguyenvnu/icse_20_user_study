private void initialize(){
  if (getSkinnable().isIndeterminate()) {
    if (timeline == null) {
      createTransition();
      if (getSkinnable().impl_isTreeVisible()) {
        timeline.play();
      }
    }
  }
 else {
    clearAnimation();
    arc.setStartAngle(90);
    updateProgress();
  }
}
