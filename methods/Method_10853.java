/** 
 * ????? (BD-09) ??? ?? GPS84 ???
 * @param bdLon ??*??
 * @param bdLat ??*??
 * @return GPS???
 */
public static Gps BD09ToGPS84(double bdLon,double bdLat){
  Gps gcj02=BD09ToGCJ02(bdLon,bdLat);
  Gps map84=GCJ02ToGPS84(gcj02.getLongitude(),gcj02.getLatitude());
  return map84;
}
