private void runTimer(JFXAnimationTimer timer,boolean animation){
  if (animation) {
    if (!timer.isRunning()) {
      timer.start();
    }
  }
 else {
    timer.applyEndValues();
  }
}
