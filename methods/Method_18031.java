/** 
 * Force the spring to clamp at its end value to avoid overshooting the target value.
 * @param overshootClampingEnabled whether or not to enable overshoot clamping
 * @return the spring for chaining
 */
public Spring setOvershootClampingEnabled(boolean overshootClampingEnabled){
  mOvershootClampingEnabled=overshootClampingEnabled;
  return this;
}
