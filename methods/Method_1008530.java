/** 
 * Sets the center point for this query. 
 */
public GeoDistanceQueryBuilder geohash(String geohash){
  if (Strings.isEmpty(geohash)) {
    throw new IllegalArgumentException("geohash must not be null or empty");
  }
  this.center.resetFromGeoHash(geohash);
  return this;
}
