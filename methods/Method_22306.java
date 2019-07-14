@SuppressWarnings("WeakerAccess") protected void handleResponse(int responseCode,String responseMessage) throws IOException {
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Request response : " + responseCode + " : " + responseMessage);
  if (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
    ACRA.log.i(LOG_TAG,"Request received by server");
  }
 else   if (responseCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT || responseCode >= HttpURLConnection.HTTP_INTERNAL_ERROR) {
    ACRA.log.w(LOG_TAG,"Could not send ACRA Post responseCode=" + responseCode + " message=" + responseMessage);
    throw new IOException("Host returned error code " + responseCode);
  }
 else   if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
    ACRA.log.w(LOG_TAG,responseCode + ": Client error - request will be discarded");
  }
 else {
    ACRA.log.w(LOG_TAG,"Could not send ACRA Post - request will be discarded. responseCode=" + responseCode + " message=" + responseMessage);
  }
}
