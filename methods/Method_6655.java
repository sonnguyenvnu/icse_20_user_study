private boolean isNearToSensor(float value){
  return value < 5.0f && value != proximitySensor.getMaximumRange();
}
