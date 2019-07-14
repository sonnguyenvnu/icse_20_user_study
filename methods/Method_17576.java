@Override protected double adjust(double hitRate){
  double currentMissRate=(1 - hitRate);
  double previousMissRate=(1 - previousHitRate);
  double gradient=currentMissRate - previousMissRate;
  moment=(beta1 * moment) + ((1 - beta1) * gradient);
  velocity=(beta2 * velocity) + ((1 - beta2) * (gradient * gradient));
  maxVelocity=Math.max(velocity,maxVelocity);
  return (stepSize * moment) / (Math.sqrt(maxVelocity) + epsilon);
}
