public static double hypot(double x,double y){
  double t;
  x=Math.abs(x);
  y=Math.abs(y);
  t=Math.min(x,y);
  x=Math.max(x,y);
  t=t / x;
  return x * Math.sqrt(1 + t * t);
}
