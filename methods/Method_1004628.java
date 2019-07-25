/** 
 * Returns the IGeoPoint which lies the given fraction of the way between the origin IGeoPoint and the destination IGeoPoint.
 * @param from     The IGeoPoint from which to start.
 * @param to       The IGeoPoint toward which to travel.
 * @param fraction A fraction of the distance to travel.
 * @return The interpolated IGeoPoint.
 */
public static IGeoPoint interpolate(IGeoPoint from,IGeoPoint to,double fraction){
  double fromLat=toRadians(from.getLatitude());
  double fromLng=toRadians(from.getLongitude());
  double toLat=toRadians(to.getLatitude());
  double toLng=toRadians(to.getLongitude());
  double cosFromLat=cos(fromLat);
  double cosToLat=cos(toLat);
  double angle=computeAngleBetween(from,to);
  double sinAngle=sin(angle);
  if (sinAngle < 1E-6) {
    return from;
  }
  double a=sin((1 - fraction) * angle) / sinAngle;
  double b=sin(fraction * angle) / sinAngle;
  double x=a * cosFromLat * cos(fromLng) + b * cosToLat * cos(toLng);
  double y=a * cosFromLat * sin(fromLng) + b * cosToLat * sin(toLng);
  double z=a * sin(fromLat) + b * sin(toLat);
  double lat=atan2(z,sqrt(x * x + y * y));
  double lng=atan2(y,x);
  return new GeoPoint(toDegrees(lat),toDegrees(lng));
}
