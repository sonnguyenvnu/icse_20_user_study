/** 
 * <p>Calculates the time for deceleration so that transition from LinearInterpolator to DecelerateInterpolator looks smooth.</p>
 * @param dx Distance to scroll
 * @return Time for DecelerateInterpolator to smoothly traverse the distance when transitioningfrom LinearInterpolation
 */
protected int calculateTimeForDeceleration(int dx){
  return (int)Math.ceil(calculateTimeForScrolling(dx) / .3356);
}
