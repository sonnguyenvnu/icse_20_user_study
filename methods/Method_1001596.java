static String format(double x){
  if (x == 0) {
    return "0";
  }
  return Long.toString((long)(x * 100));
}
