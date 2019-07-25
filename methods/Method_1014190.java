/** 
 * Returns altitude increase in altitude in degrees. Rough refraction formula using standard atmosphere: 1015 mbar and 10°C.
 */
private double refraction(double alt){
  int pressure=1015;
  int temperature=10;
  double altdeg=alt * SunCalc.RAD2DEG;
  if (altdeg < -2 || altdeg >= 90) {
    return 0;
  }
  if (altdeg > 15) {
    return 0.00452 * pressure / ((273 + temperature) * Math.tan(alt));
  }
  double y=alt;
  double d=0.0;
  double p=(pressure - 80.0) / 930.0;
  double q=0.0048 * (temperature - 10.0);
  double y0=y;
  double d0=d;
  double n=0.0;
  for (int i=0; i < 3; i++) {
    n=y + (7.31 / (y + 4.4));
    n=1.0 / Math.tan(n * SunCalc.DEG2RAD);
    d=n * p / (60.0 + q * (n + 39.0));
    n=y - y0;
    y0=d - d0 - n;
    n=((n != 0.0) && (y0 != 0.0)) ? y - n * (alt + d - y) / y0 : alt + d;
    y0=y;
    d0=d;
    y=n;
  }
  return d;
}
