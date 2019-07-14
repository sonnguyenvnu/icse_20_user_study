@Override protected byte[] doHttp(Context context,MmsNetworkManager netMgr,ApnSettings apn) throws MmsHttpException {
  final MmsHttpClient mmsHttpClient=netMgr.getOrCreateHttpClient();
  if (mmsHttpClient == null) {
    Timber.e("MMS network is not ready!");
    throw new MmsHttpException(0,"MMS network is not ready");
  }
  return mmsHttpClient.execute(mLocationUrl,null,MmsHttpClient.METHOD_GET,apn.isProxySet(),apn.getProxyAddress(),apn.getProxyPort(),mMmsConfig);
}
