/** 
 * ?? GPS84 ??? ??? [??????] ????? (GCJ-02) <p> World Geodetic System ==> Mars Geodetic System
 * @param lon ??
 * @param lat ??
 * @return GPS???
 */
public static Gps GPS84ToGCJ02(double lon,double lat){
  if (outOfChina(lon,lat)) {
    return null;
  }
  double dLat=transformLat(lon - 105.0,lat - 35.0);
  double dLon=transformLon(lon - 105.0,lat - 35.0);
  double radLat=lat / 180.0 * pi;
  double magic=Math.sin(radLat);
  magic=1 - ee * magic * magic;
  double sqrtMagic=Math.sqrt(magic);
  dLat=(dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
  dLon=(dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
  double mgLat=lat + dLat;
  double mgLon=lon + dLon;
  return new Gps(mgLon,mgLat);
}
