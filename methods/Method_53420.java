/** 
 * Returns a raw JSON form of the provided object.<br> Note that raw JSON forms can be retrieved only from the same thread invoked the last method call and will become inaccessible once another method call
 * @param obj target object to retrieve JSON
 * @return raw JSON
 * @since Twitter4J 2.1.7
 */
public static String getRawJSON(Object obj){
  if (!registeredAtleastOnce) {
    throw new IllegalStateException("Apparently jsonStoreEnabled is not set to true.");
  }
  Object json=rawJsonMap.get().get(obj);
  if (json instanceof String) {
    return (String)json;
  }
 else   if (json != null) {
    return json.toString();
  }
 else {
    return null;
  }
}
