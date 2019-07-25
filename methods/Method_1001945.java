public static boolean hev(int threshold,int p1,int p0,int q0,int q1){
  return abs(p1 - p0) > threshold || abs(q1 - q0) > threshold;
}
