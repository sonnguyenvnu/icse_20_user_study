protected double manageround(double angle){
  final double deg=angle * 180.0 / Math.PI;
  if (isCloseTo(0,deg)) {
    return 0;
  }
  if (isCloseTo(90,deg)) {
    return 90.0 * Math.PI / 180.0;
  }
  if (isCloseTo(180,deg)) {
    return 180.0 * Math.PI / 180.0;
  }
  if (isCloseTo(270,deg)) {
    return 270.0 * Math.PI / 180.0;
  }
  if (isCloseTo(360,deg)) {
    return 0;
  }
  return angle;
}
