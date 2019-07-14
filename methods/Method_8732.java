private float getCircleValue(float value){
  while (value > 360) {
    value-=360;
  }
  return value;
}
