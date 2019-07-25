public static int mix(double key,int seed){
  return (int)mix64(Double.doubleToLongBits(key) ^ seed);
}
