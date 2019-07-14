private void initPlayServices(){
  AndroidUtilities.runOnUIThread(() -> {
    if (checkPlayServices()) {
      final String currentPushString=SharedConfig.pushString;
      if (!TextUtils.isEmpty(currentPushString)) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("GCM regId = " + currentPushString);
        }
      }
 else {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("GCM Registration not found.");
        }
      }
      Utilities.globalQueue.postRunnable(() -> {
        try {
          FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            String token=instanceIdResult.getToken();
            if (!TextUtils.isEmpty(token)) {
              GcmPushListenerService.sendRegistrationToServer(token);
            }
          }
);
        }
 catch (        Throwable e) {
          FileLog.e(e);
        }
      }
);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("No valid Google Play Services APK found.");
      }
    }
  }
,1000);
}
