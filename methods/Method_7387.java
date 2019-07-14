public void handleNotificationAction(Intent intent){
  if ((getPackageName() + ".END_CALL").equals(intent.getAction())) {
    stopForeground(true);
    hangUp();
  }
 else   if ((getPackageName() + ".DECLINE_CALL").equals(intent.getAction())) {
    stopForeground(true);
    declineIncomingCall(DISCARD_REASON_LINE_BUSY,null);
  }
 else   if ((getPackageName() + ".ANSWER_CALL").equals(intent.getAction())) {
    acceptIncomingCallFromNotification();
  }
}
