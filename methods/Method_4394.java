private static byte[] executePost(HttpDataSource.Factory dataSourceFactory,String url,byte[] data,@Nullable Map<String,String> requestProperties) throws IOException {
  HttpDataSource dataSource=dataSourceFactory.createDataSource();
  if (requestProperties != null) {
    for (    Map.Entry<String,String> requestProperty : requestProperties.entrySet()) {
      dataSource.setRequestProperty(requestProperty.getKey(),requestProperty.getValue());
    }
  }
  int manualRedirectCount=0;
  while (true) {
    DataSpec dataSpec=new DataSpec(Uri.parse(url),data,0,0,C.LENGTH_UNSET,null,DataSpec.FLAG_ALLOW_GZIP);
    DataSourceInputStream inputStream=new DataSourceInputStream(dataSource,dataSpec);
    try {
      return Util.toByteArray(inputStream);
    }
 catch (    InvalidResponseCodeException e) {
      boolean manuallyRedirect=(e.responseCode == 307 || e.responseCode == 308) && manualRedirectCount++ < MAX_MANUAL_REDIRECTS;
      String redirectUrl=manuallyRedirect ? getRedirectUrl(e) : null;
      if (redirectUrl == null) {
        throw e;
      }
      url=redirectUrl;
    }
 finally {
      Util.closeQuietly(inputStream);
    }
  }
}
