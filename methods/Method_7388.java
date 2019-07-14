private void acceptIncomingCallFromNotification(){
  showNotification();
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
    try {
      PendingIntent.getActivity(VoIPBaseService.this,0,new Intent(VoIPBaseService.this,VoIPPermissionActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),0).send();
    }
 catch (    Exception x) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("Error starting permission activity",x);
      }
    }
    return;
  }
  acceptIncomingCall();
  try {
    PendingIntent.getActivity(VoIPBaseService.this,0,new Intent(VoIPBaseService.this,getUIActivityClass()).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP),0).send();
  }
 catch (  Exception x) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Error starting incall activity",x);
    }
  }
}
