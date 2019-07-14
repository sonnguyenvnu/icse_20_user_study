private static boolean isHttpSuccess(int responseCode){
  return (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE);
}
