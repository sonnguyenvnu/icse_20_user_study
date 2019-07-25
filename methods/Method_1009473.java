/** 
 * @return a new position in the given distance and angle from the original position 
 */
public static LatLon translate(LatLon pos,double distance,double angle){
  double ?1=Math.toRadians(pos.getLatitude());
  double ?1=Math.toRadians(pos.getLongitude());
  double ?1=Math.toRadians(angle);
  double ?12=distance / EARTH_RADIUS;
  double y=sin(?1) * cos(?12) + cos(?1) * sin(?12) * cos(?1);
  double a=cos(?1) * cos(?12) - sin(?1) * sin(?12) * cos(?1);
  double b=sin(?12) * sin(?1);
  double x=sqrt(sqr(a) + sqr(b));
  double ?2=atan2(y,x);
  double ?2=?1 + atan2(b,a);
  return createTranslated(Math.toDegrees(?2),Math.toDegrees(?2));
}
