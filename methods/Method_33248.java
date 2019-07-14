private static int clamp(int min,int value,int max){
  if (value < min) {
    return min;
  }
  if (value > max) {
    return max;
  }
  return value;
}
