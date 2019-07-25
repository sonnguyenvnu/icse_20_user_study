public static int mix(double key){
  return (int)mix64(Double.doubleToLongBits(key));
}
