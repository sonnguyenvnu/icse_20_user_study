public void onUIForegroundStateChanged(boolean isForeground){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)   return;
  if (currentState == STATE_WAITING_INCOMING) {
    if (isForeground) {
      stopForeground(true);
    }
 else {
      if (!((KeyguardManager)getSystemService(KEYGUARD_SERVICE)).inKeyguardRestrictedInputMode()) {
        if (NotificationManagerCompat.from(this).areNotificationsEnabled())         showIncomingNotification(ContactsController.formatName(user.first_name,user.last_name),null,user,null,0,VoIPActivity.class);
 else         declineIncomingCall(DISCARD_REASON_LINE_BUSY,null);
      }
 else {
        AndroidUtilities.runOnUIThread(new Runnable(){
          @Override public void run(){
            Intent intent=new Intent(VoIPService.this,VoIPActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            try {
              PendingIntent.getActivity(VoIPService.this,0,intent,0).send();
            }
 catch (            PendingIntent.CanceledException e) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.e("error restarting activity",e);
              }
              declineIncomingCall(DISCARD_REASON_LINE_BUSY,null);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
              showNotification();
            }
          }
        }
,500);
      }
    }
  }
}
