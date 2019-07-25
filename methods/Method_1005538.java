/** 
 * @param geocodingProvider geocoding provider we want to use
 * @return request handler for geocoding operations
 */
public GeocodingControl geocoding(GeocodingProvider geocodingProvider){
  return new GeocodingControl(this,geocodingProvider);
}
