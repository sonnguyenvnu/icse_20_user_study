/** 
 * @param localitySearch The locality name to search for.
 * @return the locality full code, or an empty string if not found.
 */
public static String getLocalityCode(@NonNull String localitySearch){
  for (  String locality : LOCALITIES) {
    if (locality.subSequence(10,locality.length()).equals(localitySearch)) {
      return locality.subSequence(0,9).toString();
    }
  }
  return "";
}
