/** 
 * ???????????
 * @param context   ???
 * @param latitude  ??
 * @param longitude ??
 * @return ????
 */
public static String getStreet(Context context,double latitude,double longitude){
  Address address=getAddress(context,latitude,longitude);
  return address == null ? "unknown" : address.getAddressLine(0);
}
