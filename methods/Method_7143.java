@Override public long open(DataSpec dataSpec) throws IOException {
  Assertions.checkState(dataSource == null);
  String scheme=dataSpec.uri.getScheme();
  if (Util.isLocalFileUri(dataSpec.uri)) {
    String uriPath=dataSpec.uri.getPath();
    if (uriPath != null && uriPath.startsWith("/android_asset/")) {
      dataSource=getAssetDataSource();
    }
 else {
      if (dataSpec.uri.getPath().endsWith(".enc")) {
        dataSource=getEncryptedFileDataSource();
      }
 else {
        dataSource=getFileDataSource();
      }
    }
  }
 else   if ("tg".equals(scheme)) {
    dataSource=getStreamDataSource();
  }
 else   if (SCHEME_ASSET.equals(scheme)) {
    dataSource=getAssetDataSource();
  }
 else   if (SCHEME_CONTENT.equals(scheme)) {
    dataSource=getContentDataSource();
  }
 else   if (SCHEME_RTMP.equals(scheme)) {
    dataSource=getRtmpDataSource();
  }
 else   if (DataSchemeDataSource.SCHEME_DATA.equals(scheme)) {
    dataSource=getDataSchemeDataSource();
  }
 else   if (SCHEME_RAW.equals(scheme)) {
    dataSource=getRawResourceDataSource();
  }
 else {
    dataSource=baseDataSource;
  }
  return dataSource.open(dataSpec);
}
