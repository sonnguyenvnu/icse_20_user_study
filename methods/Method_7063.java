@Override public BrowserRoot onGetRoot(String clientPackageName,int clientUid,Bundle rootHints){
  if (clientPackageName == null || Process.SYSTEM_UID != clientUid && Process.myUid() != clientUid && !clientPackageName.equals("com.google.android.mediasimulator") && !clientPackageName.equals("com.google.android.projection.gearhead")) {
    return null;
  }
  return new BrowserRoot(MEDIA_ID_ROOT,null);
}
