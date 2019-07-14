@Override public long open(DataSpec dataSpec) throws HttpDataSourceException {
  this.dataSpec=dataSpec;
  this.bytesRead=0;
  this.bytesSkipped=0;
  transferInitializing(dataSpec);
  try {
    connection=makeConnection(dataSpec);
  }
 catch (  IOException e) {
    throw new HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(),e,dataSpec,HttpDataSourceException.TYPE_OPEN);
  }
  int responseCode;
  String responseMessage;
  try {
    responseCode=connection.getResponseCode();
    responseMessage=connection.getResponseMessage();
  }
 catch (  IOException e) {
    closeConnectionQuietly();
    throw new HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(),e,dataSpec,HttpDataSourceException.TYPE_OPEN);
  }
  if (responseCode < 200 || responseCode > 299) {
    Map<String,List<String>> headers=connection.getHeaderFields();
    closeConnectionQuietly();
    InvalidResponseCodeException exception=new InvalidResponseCodeException(responseCode,responseMessage,headers,dataSpec);
    if (responseCode == 416) {
      exception.initCause(new DataSourceException(DataSourceException.POSITION_OUT_OF_RANGE));
    }
    throw exception;
  }
  String contentType=connection.getContentType();
  if (contentTypePredicate != null && !contentTypePredicate.evaluate(contentType)) {
    closeConnectionQuietly();
    throw new InvalidContentTypeException(contentType,dataSpec);
  }
  bytesToSkip=responseCode == 200 && dataSpec.position != 0 ? dataSpec.position : 0;
  if (!dataSpec.isFlagSet(DataSpec.FLAG_ALLOW_GZIP)) {
    if (dataSpec.length != C.LENGTH_UNSET) {
      bytesToRead=dataSpec.length;
    }
 else {
      long contentLength=getContentLength(connection);
      bytesToRead=contentLength != C.LENGTH_UNSET ? (contentLength - bytesToSkip) : C.LENGTH_UNSET;
    }
  }
 else {
    bytesToRead=dataSpec.length;
  }
  try {
    inputStream=connection.getInputStream();
  }
 catch (  IOException e) {
    closeConnectionQuietly();
    throw new HttpDataSourceException(e,dataSpec,HttpDataSourceException.TYPE_OPEN);
  }
  opened=true;
  transferStarted(dataSpec);
  return bytesToRead;
}
