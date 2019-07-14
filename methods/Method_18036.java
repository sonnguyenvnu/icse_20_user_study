/** 
 * check if the current state is at rest
 * @return is the spring at rest
 */
public boolean isAtRest(){
  return Math.abs(mCurrentState.velocity) <= mRestSpeedThreshold && (getDisplacementDistanceForState(mCurrentState) <= mDisplacementFromRestThreshold || mSpringConfig.tension == 0);
}
