/** 
 * creates Ripple effect
 */
protected void createRipple(double x,double y){
  if (!isRipplerDisabled()) {
    rippler.setGeneratorCenterX(x);
    rippler.setGeneratorCenterY(y);
    rippler.createRipple();
  }
}
