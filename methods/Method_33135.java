/** 
 * creates Ripple effect in the center of the control
 * @return a runnable to release the ripple when needed
 */
public Runnable createManualRipple(){
  if (!isRipplerDisabled()) {
    rippler.setGeneratorCenterX(control.getLayoutBounds().getWidth() / 2);
    rippler.setGeneratorCenterY(control.getLayoutBounds().getHeight() / 2);
    rippler.createRipple();
    return () -> {
      releaseRipple();
    }
;
  }
  return () -> {
  }
;
}
