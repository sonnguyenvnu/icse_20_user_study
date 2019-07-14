/** 
 * If this is set to true, parent views can intercept touch events while the view is zoomed. For example, this can be used to swipe between images in a view pager while zoomed.
 * @param allowTouchInterceptionWhileZoomed true if the parent needs to intercept touches
 */
public void setAllowTouchInterceptionWhileZoomed(boolean allowTouchInterceptionWhileZoomed){
  mAllowTouchInterceptionWhileZoomed=allowTouchInterceptionWhileZoomed;
}
