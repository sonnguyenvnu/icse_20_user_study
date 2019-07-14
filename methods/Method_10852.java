/** 
 * ????? (BD-09) ??? ????? (GCJ-02)
 * @param bdLon ??*??
 * @param bdLat ??*??
 * @return GPS???
 */
public static Gps BD09ToGCJ02(double bdLon,double bdLat){
  double x=bdLon - 0.0065, y=bdLat - 0.006;
  double z=Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
  double theta=Math.atan2(y,x) - 0.000003 * Math.cos(x * pi);
  double ggLon=z * Math.cos(theta);
  double ggLat=z * Math.sin(theta);
  return new Gps(ggLon,ggLat);
}
