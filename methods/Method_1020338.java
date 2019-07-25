private void download(String urlStr,File outputJar) throws IOException {
  URL url=new URL(urlStr);
  Log.d(TAG,"Downloading " + outputJar.getPath());
  URLConnection connection=url.openConnection();
  connection.setReadTimeout(30000);
  connection.setConnectTimeout(15000);
  InputStream inputStream=connection.getInputStream();
  OutputStream outputStream=new FileOutputStream(outputJar);
  byte[] buffer=new byte[2048];
  int length;
  while ((length=inputStream.read(buffer)) > 0) {
    outputStream.write(buffer,0,length);
  }
  inputStream.close();
  outputStream.close();
  Log.d(TAG,"Download complete");
}
