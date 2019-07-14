/** 
 * Encodes latitude/longitude into Open Location Code of the provided length. This method is equivalent to creating the OpenLocationCode object and getting the code from it.
 * @param latitude The latitude in decimal degrees.
 * @param longitude The longitude in decimal degrees.
 * @param codeLength The number of digits in the returned code.
 * @return The code.
 */
public static String encode(double latitude,double longitude,int codeLength){
  return new OpenLocationCode(latitude,longitude,codeLength).getCode();
}
