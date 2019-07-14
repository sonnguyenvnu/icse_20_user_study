@SuppressWarnings("WeakerAccess") protected void configureTimeouts(@NonNull HttpURLConnection connection,int connectionTimeOut,int socketTimeOut){
  connection.setConnectTimeout(connectionTimeOut);
  connection.setReadTimeout(socketTimeOut);
}
