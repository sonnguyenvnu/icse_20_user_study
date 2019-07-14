/** 
 * Whether the given coordinates mark a point on earth.
 * @param latitude
 * @param longitude
 * @return
 */
public static boolean isValidCoordinate(final double latitude,final double longitude){
  return latitude >= -90.0 && latitude <= 90.0 && longitude >= -180.0 && longitude <= 180.0;
}
