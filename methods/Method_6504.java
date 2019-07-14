@Override public void onNewToken(String token){
  AndroidUtilities.runOnUIThread(() -> {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("Refreshed token: " + token);
    }
    ApplicationLoader.postInitApplication();
    sendRegistrationToServer(token);
  }
);
}
