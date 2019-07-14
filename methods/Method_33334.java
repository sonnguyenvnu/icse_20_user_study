private void pauseTimeline(boolean pause){
  if (getSkinnable().isIndeterminate()) {
    if (timeline == null) {
      createTransition();
    }
    if (pause) {
      timeline.pause();
    }
 else {
      timeline.play();
    }
  }
}
