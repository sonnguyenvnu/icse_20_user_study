/** 
 * compute the ripple radius
 * @return the ripple radius size
 */
protected double computeRippleRadius(){
  double width2=control.getLayoutBounds().getWidth() * control.getLayoutBounds().getWidth();
  double height2=control.getLayoutBounds().getHeight() * control.getLayoutBounds().getHeight();
  return Math.min(Math.sqrt(width2 + height2),RIPPLE_MAX_RADIUS) * 1.1 + 5;
}
