public static int dpr(float value){
  if (value == 0) {
    return 0;
  }
  return Math.round(density * value);
}
