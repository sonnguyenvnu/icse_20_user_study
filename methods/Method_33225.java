private void playClickAnimation(double rate){
  if (clickedAnimation != null) {
    if (!clickedAnimation.getCurrentTime().equals(clickedAnimation.getCycleDuration()) || rate != 1) {
      clickedAnimation.setRate(rate);
      clickedAnimation.play();
    }
  }
}
