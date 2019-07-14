/** 
 * returns tweets by users located within a given radius of the given latitude/longitude, where the user's location is taken from their Twitter profile
 * @param location geo location
 * @param radius   radius
 * @param unit     Query.MILES or Query.KILOMETERS
 * @return the instance
 * @since Twitter4J 4.0.7
 */
public Query geoCode(GeoLocation location,double radius,Unit unit){
  setGeoCode(location,radius,unit);
  return this;
}
