private boolean isConnected(){
  ConnectivityManager mConnMgr=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo ni=mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE_MMS);
  return (ni == null ? false : ni.isConnected());
}
