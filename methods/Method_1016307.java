protected SteeringAcceleration<T> arrive(SteeringAcceleration<T> steering,T targetPosition){
  T toTarget=steering.linear.set(targetPosition).sub(owner.getPosition());
  float distance=toTarget.len();
  if (distance <= arrivalTolerance)   return steering.setZero();
  Limiter actualLimiter=getActualLimiter();
  float targetSpeed=actualLimiter.getMaxLinearSpeed();
  if (distance <= decelerationRadius)   targetSpeed*=distance / decelerationRadius;
  T targetVelocity=toTarget.scl(targetSpeed / distance);
  targetVelocity.sub(owner.getLinearVelocity()).scl(1f / timeToTarget).limit(actualLimiter.getMaxLinearAcceleration());
  steering.angular=0f;
  return steering;
}
