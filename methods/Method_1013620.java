protected void initialize(String ntpHost) throws IOException {
  if (isInitialized()) {
    TrueLog.i(TAG,"---- TrueTime already initialized from previous boot/init");
    return;
  }
  requestTime(ntpHost);
  saveTrueTimeInfoToDisk();
}
