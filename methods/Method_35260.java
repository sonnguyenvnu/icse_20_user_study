private static float toTangent(float arcInDegrees){
  if (arcInDegrees < 0 || arcInDegrees > 90) {
    throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
  }
  return (float)Math.tan(Math.toRadians(arcInDegrees / 2));
}
