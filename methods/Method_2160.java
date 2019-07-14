/** 
 * Check whether the parent view can intercept touch events while zoomed. This can be used, for example, to swipe between images in a view pager while zoomed.
 * @return true if touch events can be intercepted
 */
public boolean allowsTouchInterceptionWhileZoomed(){
  return mAllowTouchInterceptionWhileZoomed;
}
