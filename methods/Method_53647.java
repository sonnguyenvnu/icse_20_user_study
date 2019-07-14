/** 
 * Cubic Hermite spline slope differentiated
 */
private static double diff(double h,double x,double y1,double y2,double t1,double t2){
  double x2=x * x;
  return -6 * x2 * y2 + 6 * x * y2 + 6 * x2 * y1 - 6 * x * y1 + 3 * h * t2 * x2 + 3 * h * t1 * x2 - 2 * h * t2 * x - 4 * h * t1 * x + h * t1;
}
