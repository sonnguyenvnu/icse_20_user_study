@Override protected Uri persistIfRequired(Context context,int result,byte[] response){
  if (!mRequestManager.getAutoPersistingPref()) {
    notifyOfDownload(context);
    return null;
  }
  return persist(context,response,mMmsConfig,mLocationUrl,mSubId,mCreator);
}
