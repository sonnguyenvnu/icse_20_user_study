/** 
 * Adjusts ClearKey response data to be suitable for providing to the Android ClearKey CDM.
 * @param response The response data.
 * @return The adjusted response data.
 */
public static byte[] adjustResponseData(byte[] response){
  if (Util.SDK_INT >= 27) {
    return response;
  }
  try {
    JSONObject responseJson=new JSONObject(Util.fromUtf8Bytes(response));
    StringBuilder adjustedResponseBuilder=new StringBuilder("{\"keys\":[");
    JSONArray keysArray=responseJson.getJSONArray("keys");
    for (int i=0; i < keysArray.length(); i++) {
      if (i != 0) {
        adjustedResponseBuilder.append(",");
      }
      JSONObject key=keysArray.getJSONObject(i);
      adjustedResponseBuilder.append("{\"k\":\"");
      adjustedResponseBuilder.append(base64UrlToBase64(key.getString("k")));
      adjustedResponseBuilder.append("\",\"kid\":\"");
      adjustedResponseBuilder.append(base64UrlToBase64(key.getString("kid")));
      adjustedResponseBuilder.append("\",\"kty\":\"");
      adjustedResponseBuilder.append(key.getString("kty"));
      adjustedResponseBuilder.append("\"}");
    }
    adjustedResponseBuilder.append("]}");
    return Util.getUtf8Bytes(adjustedResponseBuilder.toString());
  }
 catch (  JSONException e) {
    Log.e(TAG,"Failed to adjust response data: " + Util.fromUtf8Bytes(response),e);
    return response;
  }
}
