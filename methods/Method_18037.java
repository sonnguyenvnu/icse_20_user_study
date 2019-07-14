/** 
 * linear interpolation between the previous and current physics state based on the amount of timestep remaining after processing the rendering delta time in timestep sized chunks.
 * @param alpha from 0 to 1, where 0 is the previous state, 1 is the current state
 */
private void interpolate(double alpha){
  mCurrentState.position=mCurrentState.position * alpha + mPreviousState.position * (1 - alpha);
  mCurrentState.velocity=mCurrentState.velocity * alpha + mPreviousState.velocity * (1 - alpha);
}
