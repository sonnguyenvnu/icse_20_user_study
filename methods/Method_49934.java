public void setConnectionSettings(String mmscUrl,String proxyAddress,int proxyPort){
  mBundle.putString(MMSC_URL,mmscUrl);
  mBundle.putString(PROXY_ADDRESS,proxyAddress);
  mBundle.putInt(PROXY_PORT,proxyPort);
}
