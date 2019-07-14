/** 
 * Calculate whether the coordinates are touching the AM or PM circle.
 */
public int getIsTouchingAmOrPm(float xCoord,float yCoord){
  if (!mDrawValuesReady) {
    return -1;
  }
  int squaredYDistance=(int)((yCoord - mAmPmYCenter) * (yCoord - mAmPmYCenter));
  int distanceToAmCenter=(int)Math.sqrt((xCoord - mAmXCenter) * (xCoord - mAmXCenter) + squaredYDistance);
  if (distanceToAmCenter <= mAmPmCircleRadius) {
    return AM;
  }
  int distanceToPmCenter=(int)Math.sqrt((xCoord - mPmXCenter) * (xCoord - mPmXCenter) + squaredYDistance);
  if (distanceToPmCenter <= mAmPmCircleRadius) {
    return PM;
  }
  return -1;
}
