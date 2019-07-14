/** 
 * get the displacement from rest for a given physics state
 * @param state the state to measure from
 * @return the distance displaced by
 */
private double getDisplacementDistanceForState(PhysicsState state){
  return Math.abs(mEndValue - state.position);
}
