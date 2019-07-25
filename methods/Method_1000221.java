public void hide(){
  if (musicControllerAnimationSet != null) {
    musicControllerAnimationSet.cancel();
  }
  prepareForAnimation(playPauseButton);
  prepareForAnimation(nextButton);
  prepareForAnimation(prevButton);
  prepareForAnimation(shuffleButton);
  prepareForAnimation(repeatButton);
  hidden=true;
}
