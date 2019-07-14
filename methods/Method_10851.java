/** 
 * ????? (GCJ-02) ??? ????? (BD-09)
 * @param ggLon ??
 * @param ggLat ??
 */
public static Gps GCJ02ToBD09(double ggLon,double ggLat){
  double x=ggLon, y=ggLat;
  double z=Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
  double theta=Math.atan2(y,x) + 0.000003 * Math.cos(x * pi);
  double bdLon=z * Math.cos(theta) + 0.0065;
  double bdLat=z * Math.sin(theta) + 0.006;
  return new Gps(bdLon,bdLat);
}
