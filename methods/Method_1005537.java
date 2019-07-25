/** 
 * @return request handler for geocoding operations
 */
public GeocodingControl geocoding(){
  return geocoding(new AndroidGeocodingProvider());
}
