private static boolean floatsEqual(float f1,float f2){
  if (Float.isNaN(f1) || Float.isNaN(f2)) {
    return Float.isNaN(f1) && Float.isNaN(f2);
  }
  return Math.abs(f2 - f1) < .00001f;
}
