protected void startRinging(){
  if (currentState == STATE_WAITING_INCOMING) {
    return;
  }
  if (USE_CONNECTION_SERVICE && systemCallConnection != null)   systemCallConnection.setRinging();
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("starting ringing for call " + call.id);
  }
  dispatchStateChanged(STATE_WAITING_INCOMING);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    showIncomingNotification(ContactsController.formatName(user.first_name,user.last_name),null,user,null,0,VoIPActivity.class);
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("Showing incoming call notification");
    }
  }
 else {
    startRingtoneAndVibration(user.id);
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("Starting incall activity for incoming call");
    }
    try {
      PendingIntent.getActivity(VoIPService.this,12345,new Intent(VoIPService.this,VoIPActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),0).send();
    }
 catch (    Exception x) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("Error starting incall activity",x);
      }
    }
  }
}
