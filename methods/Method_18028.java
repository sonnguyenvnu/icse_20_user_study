/** 
 * get the displacement of the springs current value from its rest value.
 * @return the distance displaced by
 */
public double getCurrentDisplacementDistance(){
  return getDisplacementDistanceForState(mCurrentState);
}
