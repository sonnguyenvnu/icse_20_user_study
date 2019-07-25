protected SteeringAcceleration<T> face(SteeringAcceleration<T> steering,T targetPosition){
  T toTarget=steering.linear.set(targetPosition).sub(owner.getPosition());
  if (toTarget.isZero(getActualLimiter().getZeroLinearSpeedThreshold()))   return steering.setZero();
  float orientation=owner.vectorToAngle(toTarget);
  return reachOrientation(steering,orientation);
}
