public int colorForLocation(float location){
  if (location <= 0) {
    return COLORS[0];
  }
 else   if (location >= 1) {
    return COLORS[COLORS.length - 1];
  }
  int leftIndex=-1;
  int rightIndex=-1;
  for (int i=1; i < LOCATIONS.length; i++) {
    float value=LOCATIONS[i];
    if (value > location) {
      leftIndex=i - 1;
      rightIndex=i;
      break;
    }
  }
  float leftLocation=LOCATIONS[leftIndex];
  int leftColor=COLORS[leftIndex];
  float rightLocation=LOCATIONS[rightIndex];
  int rightColor=COLORS[rightIndex];
  float factor=(location - leftLocation) / (rightLocation - leftLocation);
  return interpolateColors(leftColor,rightColor,factor);
}
