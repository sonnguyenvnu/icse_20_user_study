private void resetOverLay(){
  if (rippler.overlayRect != null) {
    rippler.overlayRect.inAnimation.stop();
    final RippleGenerator.OverLayRipple oldOverlay=rippler.overlayRect;
    rippler.overlayRect.outAnimation.setOnFinished((finish) -> rippler.getChildren().remove(oldOverlay));
    rippler.overlayRect.outAnimation.play();
    rippler.overlayRect=null;
  }
}
