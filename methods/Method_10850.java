/** 
 * [??????] ????? (GCJ-02) ??? ?? GPS84 ???
 * @param lon ????
 * @param lat ????
 */
public static Gps GCJ02ToGPS84(double lon,double lat){
  Gps gps=transform(lon,lat);
  double lontitude=lon * 2 - gps.getLongitude();
  double latitude=lat * 2 - gps.getLatitude();
  return new Gps(lontitude,latitude);
}
