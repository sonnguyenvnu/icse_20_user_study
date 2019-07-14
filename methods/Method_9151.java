public void startAnimatingArrows(){
  if (animatingArrows || arrowAnim == null)   return;
  animatingArrows=true;
  if (arrowAnim != null) {
    arrowAnim.start();
  }
}
