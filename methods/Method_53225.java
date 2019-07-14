/** 
 * returns tweets by users located within a given radius of the given latitude/longitude, where the user's location is taken from their Twitter profile
 * @param location geo location
 * @param radius   radius
 * @param unit     Query.MILES or Query.KILOMETERS
 * @since Twitter4J 4.0.1
 */
public void setGeoCode(GeoLocation location,double radius,Unit unit){
  this.geocode=location.getLatitude() + "," + location.getLongitude() + "," + radius + unit.name();
}
