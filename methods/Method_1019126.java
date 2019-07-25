@Nullable private static byte[] http(@NonNull String http) throws IOException {
  URL url=new URL(http);
  HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
  httpURLConnection.setConnectTimeout(5000);
  httpURLConnection.setReadTimeout(5000);
  InputStream in=new BufferedInputStream(httpURLConnection.getInputStream());
  byte[] bytes=getBytes(in);
  httpURLConnection.disconnect();
  return bytes;
}
