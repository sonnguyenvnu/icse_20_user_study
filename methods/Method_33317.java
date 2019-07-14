@Override protected void pauseTimeline(boolean pause){
  if (getSkinnable().isIndeterminate()) {
    if (indeterminateTransition == null) {
      createIndeterminateTimeline();
    }
    if (pause) {
      indeterminateTransition.pause();
    }
 else {
      indeterminateTransition.play();
    }
  }
}
