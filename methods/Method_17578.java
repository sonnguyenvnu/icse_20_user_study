@Override protected double adjust(double hitRate){
  double currentMissRate=(1 - hitRate);
  double previousMissRate=(1 - previousHitRate);
  double gradient=currentMissRate - previousMissRate;
  moment=(beta1 * moment) + ((1 - beta1) * gradient);
  velocity=(beta2 * velocity) + ((1 - beta2) * (gradient * gradient));
  double momentBias=moment / (1 - Math.pow(beta1,t));
  double velocityBias=velocity / (1 - Math.pow(beta2,t));
  return (stepSize / (Math.sqrt(velocityBias) + epsilon)) * ((beta1 * momentBias) + (((1 - beta1) / (1 - Math.pow(beta1,t))) * gradient));
}
