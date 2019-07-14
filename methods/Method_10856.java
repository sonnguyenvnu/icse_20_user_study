/** 
 * ????
 * @param lon ??
 * @param lat ??
 * @return  GPS??
 */
private static Gps transform(double lon,double lat){
  if (outOfChina(lon,lat)) {
    return new Gps(lon,lat);
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
