/** 
 * Returns the maximum number of streaks this view is able to show, given its current size.
 * @return max number of visible streaks
 */
public int getMaxStreakCount(){
  return (int)Math.floor(getMeasuredHeight() / baseSize);
}
