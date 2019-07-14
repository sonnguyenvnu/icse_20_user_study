@SuppressWarnings("WeakerAccess") protected void configureHeaders(@NonNull HttpURLConnection connection,@Nullable String login,@Nullable String password,@Nullable Map<String,String> customHeaders,@NonNull T t) throws IOException {
  connection.setRequestProperty("User-Agent",String.format("Android ACRA %1$s",BuildConfig.VERSION_NAME));
  connection.setRequestProperty("Accept","text/html,application/xml,application/json,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
  connection.setRequestProperty("Content-Type",getContentType(context,t));
  if (login != null && password != null) {
    final String credentials=login + ':' + password;
    final String encoded=new String(Base64.encode(credentials.getBytes(ACRAConstants.UTF8),Base64.NO_WRAP),ACRAConstants.UTF8);
    connection.setRequestProperty("Authorization","Basic " + encoded);
  }
  if (senderConfiguration.compress()) {
    connection.setRequestProperty("Content-Encoding","gzip");
  }
  if (customHeaders != null) {
    for (    final Map.Entry<String,String> header : customHeaders.entrySet()) {
      connection.setRequestProperty(header.getKey(),header.getValue());
    }
  }
}
