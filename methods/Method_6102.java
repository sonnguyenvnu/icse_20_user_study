public static void setWaitingForSms(boolean value){
synchronized (smsLock) {
    waitingForSms=value;
    try {
      if (waitingForSms) {
        SmsRetrieverClient client=SmsRetriever.getClient(ApplicationLoader.applicationContext);
        Task<Void> task=client.startSmsRetriever();
        task.addOnSuccessListener(aVoid -> {
          if (BuildVars.DEBUG_VERSION) {
            FileLog.d("sms listener registered");
          }
        }
);
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
}
