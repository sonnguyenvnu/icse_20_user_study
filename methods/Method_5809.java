@Override protected DefaultHttpDataSource createDataSourceInternal(HttpDataSource.RequestProperties defaultRequestProperties){
  DefaultHttpDataSource dataSource=new DefaultHttpDataSource(userAgent,null,connectTimeoutMillis,readTimeoutMillis,allowCrossProtocolRedirects,defaultRequestProperties);
  if (listener != null) {
    dataSource.addTransferListener(listener);
  }
  return dataSource;
}
