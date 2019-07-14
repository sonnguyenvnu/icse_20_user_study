/** 
 * Tries to parse a payload string into a  {@link AndroidPayPayload} object.
 * @param payloadString An (optional) string of JSON that represents the payload.
 * @return The parsed {@link AndroidPayPayload} object if successful, and `null` otherwise.
 */
public static @Nullable AndroidPayPayload payloadFromString(final @Nullable String payloadString,final @NonNull Gson gson){
  if (payloadString == null) {
    return null;
  }
  final String json=new String(Base64.decode(payloadString,Base64.DEFAULT));
  return gson.fromJson(json,AndroidPayPayload.class);
}
