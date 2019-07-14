/** 
 * ???????z????p??????????<br> http://jamesmccaffrey.wordpress.com/2010/11/05/programmatically-computing-the-area-under-the-normal-curve/
 * @param z ??????????
 * @return ???????????
 */
public static double GaussCdf(double z){
  double y;
  double p;
  double w;
  if (z == 0.0) {
    p=0.0;
  }
 else {
    y=Math.abs(z) / 2.0;
    if (y >= 3.0) {
      p=1.0;
    }
 else     if (y < 1.0) {
      w=y * y;
      p=((((((((0.000124818987 * w - 0.001075204047) * w + 0.005198775019) * w - 0.019198292004) * w + 0.059054035642) * w - 0.151968751364) * w + 0.319152932694) * w - 0.531923007300) * w + 0.797884560593) * y * 2.0;
    }
 else {
      y=y - 2.0;
      p=(((((((((((((-0.000045255659 * y + 0.000152529290) * y - 0.000019538132) * y - 0.000676904986) * y + 0.001390604284) * y - 0.000794620820) * y - 0.002034254874) * y + 0.006549791214) * y - 0.010557625006) * y + 0.011630447319) * y - 0.009279453341) * y + 0.005353579108) * y - 0.002141268741) * y + 0.000535310849) * y + 0.999936657524;
    }
  }
  if (z > 0.0) {
    return (p + 1.0) / 2.0;
  }
  return (1.0 - p) / 2.0;
}