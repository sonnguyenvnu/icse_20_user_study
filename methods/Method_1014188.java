private double[] QUAD(double yminus,double yo,double yplus){
  double nz=0;
  double a=.5 * (yminus + yplus) - yo;
  double b=.5 * (yplus - yminus);
  double c=yo;
  double xe=-b / (2 * a);
  double ye=(a * xe + b) * xe + c;
  double dis=b * b - 4 * a * c;
  double zero1=0;
  double zero2=0;
  if (dis >= 0) {
    double dx=.5 * Math.sqrt(dis) / Math.abs(a);
    zero1=xe - dx;
    zero2=xe + dx;
    if (Math.abs(zero1) <= 1) {
      nz+=1;
    }
    if (Math.abs(zero2) <= 1) {
      nz+=1;
    }
    if (zero1 < -1) {
      zero1=zero2;
    }
  }
  return new double[]{ye,zero1,zero2,nz};
}
