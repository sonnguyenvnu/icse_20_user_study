/** 
 * Cubic Hermite spline
 */
private static double interpolate(double h,double x,double y1,double y2,double t1,double t2){
  double x2=x * x;
  double x3=x2 * x;
  return -2 * x3 * y2 + 3 * x2 * y2 + 2 * x3 * y1 - 3 * x2 * y1 + y1 + h * t2 * x3 + h * t1 * x3 - h * t2 * x2 - 2 * h * t1 * x2 + h * t1 * x;
}
