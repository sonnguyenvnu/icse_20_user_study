/** 
 * @return initial bearing from one point to the other.<br/>If you take a globe and draw a line straight up to the north pole from pos1 and a second one that connects pos1 and pos2, this is the angle between those two lines 
 */
public static double bearing(LatLon pos1,LatLon pos2){
  double bearing=Math.toDegrees(bearing(Math.toRadians(pos1.getLatitude()),Math.toRadians(pos1.getLongitude()),Math.toRadians(pos2.getLatitude()),Math.toRadians(pos2.getLongitude())));
  if (bearing < 0)   bearing+=360;
  if (bearing >= 360)   bearing-=360;
  return bearing;
}
