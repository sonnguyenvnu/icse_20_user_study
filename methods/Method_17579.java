@Override protected double adjust(double hitRate){
  double currentMissRate=(1 - hitRate);
  double previousMissRate=(1 - previousHitRate);
  double gradient=currentMissRate - previousMissRate;
switch (acceleration) {
case NONE:
    return stepSize * gradient;
case MOMENTUM:
  velocity=(beta * velocity) + (1 - beta) * gradient;
return stepSize * velocity;
case NESTEROV:
double previousVelocity=velocity;
velocity=(beta * velocity) + stepSize * gradient;
return -(beta * previousVelocity) + ((1 + beta) * velocity);
}
throw new IllegalStateException("Unknown acceleration type: " + acceleration);
}
