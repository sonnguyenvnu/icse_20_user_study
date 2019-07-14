private void startRatingActivity(){
  try {
    PendingIntent.getActivity(VoIPService.this,0,new Intent(VoIPService.this,VoIPFeedbackActivity.class).putExtra("call_id",call.id).putExtra("call_access_hash",call.access_hash).putExtra("account",currentAccount).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP),0).send();
  }
 catch (  Exception x) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Error starting incall activity",x);
    }
  }
}
