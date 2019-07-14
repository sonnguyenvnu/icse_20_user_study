static public Drive getDriveService(String token){
  GoogleCredential credential=new GoogleCredential().setAccessToken(token);
  return new Drive.Builder(HTTP_TRANSPORT,JSON_FACTORY,credential).setHttpRequestInitializer(new HttpRequestInitializer(){
    @Override public void initialize(    HttpRequest httpRequest) throws IOException {
      credential.initialize(httpRequest);
      httpRequest.setConnectTimeout(3 * 60000);
      httpRequest.setReadTimeout(3 * 60000);
    }
  }
).setApplicationName(SERVICE_APP_NAME).build();
}
