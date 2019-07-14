/** 
 * Adjusts ClearKey request data obtained from the Android ClearKey CDM to be spec compliant.
 * @param request The request data.
 * @return The adjusted request data.
 */
public static byte[] adjustRequestData(byte[] request){
  if (Util.SDK_INT >= 27) {
    return request;
  }
  String requestString=Util.fromUtf8Bytes(request);
  return Util.getUtf8Bytes(base64ToBase64Url(requestString));
}
