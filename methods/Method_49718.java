/** 
 * Encodes latitude/longitude into 10 digit Open Location Code. This method is equivalent to creating the OpenLocationCode object and getting the code from it.
 * @param latitude The latitude in decimal degrees.
 * @param longitude The longitude in decimal degrees.
 * @return The code.
 */
public static String encode(double latitude,double longitude){
  return new OpenLocationCode(latitude,longitude).getCode();
}
