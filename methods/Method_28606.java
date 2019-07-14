private int solveVelocity(int velocity){
  if (velocity > 0) {
    return Math.min(velocity,FLING_MAX_VELOCITY);
  }
 else {
    return Math.max(velocity,-FLING_MAX_VELOCITY);
  }
}
