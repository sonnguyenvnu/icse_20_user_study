/** 
 * Returns the locality name nearest to the passed location or throws a NoLocalityException if none are near enough to be used.
 */
public static String getNearestLocality(OpenLocationCode location) throws NoLocalityException {
  String nearestLocality=null;
  double distanceDegrees=Float.MAX_VALUE;
  CodeArea locationArea=location.decode();
  for (int i=0; i < LOCALITIES.size(); i++) {
    String localityEntry=LOCALITIES.get(i);
    String[] parts=localityEntry.split(":");
    OpenLocationCode localityCode=new OpenLocationCode(parts[0]);
    CodeArea localityArea=localityCode.decode();
    double localityDistanceDegrees=Math.max(Math.abs(localityArea.getCenterLatitude() - locationArea.getCenterLatitude()),Math.abs(localityArea.getCenterLongitude() - locationArea.getCenterLongitude()));
    if (localityDistanceDegrees >= MAX_DISTANCE_DEGREES) {
      continue;
    }
    if (localityDistanceDegrees < distanceDegrees) {
      nearestLocality=parts[1];
      distanceDegrees=localityDistanceDegrees;
    }
  }
  if (nearestLocality == null) {
    throw new NoLocalityException();
  }
  return nearestLocality;
}
