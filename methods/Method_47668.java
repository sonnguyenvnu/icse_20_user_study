private int percentageToColor(float percentage){
  if (percentage >= 1.0f)   return colors[3];
  if (percentage >= 0.8f)   return colors[2];
  if (percentage >= 0.5f)   return colors[1];
  return colors[0];
}
