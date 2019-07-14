/** 
 * Calculated the estimated scroll distance in each direction given velocities on both axes.
 * @param velocityX     Fling velocity on the horizontal axis.
 * @param velocityY     Fling velocity on the vertical axis.
 * @return array holding the calculated distances in x and y directionsrespectively.
 */
public int[] calculateScrollDistance(int velocityX,int velocityY){
  int[] outDist=new int[2];
  mGravityScroller.fling(0,0,velocityX,velocityY,Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
  outDist[0]=mGravityScroller.getFinalX();
  outDist[1]=mGravityScroller.getFinalY();
  return outDist;
}
