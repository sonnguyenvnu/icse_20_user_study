/** 
 * Cubic Hermite spline
 */
private static double length(double h,double x,double y1,double y2,double t1,double t2){
  double x2=x * x;
  double x3=x2 * x;
  double x4=x3 * x;
  return (-(x4 * y2) / 2) + x3 * y2 + (x4 * y1) / 2 - x3 * y1 + x * y1 + (h * t2 * x4) / 4 + (h * t1 * x4) / 4 - (h * t2 * x3) / 3 + ((-2) * h * t1 * x3) / 3 + (h * t1 * x2) / 2;
}
