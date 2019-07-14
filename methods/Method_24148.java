protected static boolean diff(float a,float b){
  return PGL.FLOAT_EPS <= Math.abs(a - b);
}
